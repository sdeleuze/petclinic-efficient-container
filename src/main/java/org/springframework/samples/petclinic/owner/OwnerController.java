/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private final OwnerRepository owners;
	private final PetRepository pets;
	private final VisitRepository visits;

	public OwnerController(OwnerRepository clinicService, PetRepository pets, VisitRepository visits) {
		this.owners = clinicService;
		this.pets = pets;
		this.visits = visits;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			this.owners.save(owner);
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping("/owners/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("owner", new Owner());
		return "owners/findOwners";
	}

	@GetMapping("/owners")
	public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		List<OwnerDetails> results = this.owners.findByLastName(owner.getLastName()).stream()
				.map(o -> new OwnerDetails(o, this.pets.findByOwnerId(o.getId()))).collect(Collectors.toList());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if (results.size() == 1) {
			// 1 owner found
			owner = results.iterator().next().getOwner();
			return "redirect:/owners/" + owner.getId();
		} else {
			// multiple owners found
			model.put("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = this.owners.findById(ownerId);
		model.addAttribute(owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(ownerId);
			this.owners.save(owner);
			return "redirect:/owners/{ownerId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(this.owners.findById(ownerId));
		mav.addObject("pets",
				this.pets.findByOwnerId(ownerId).stream()
						.map(pet -> new PetDetails(pet, this.pets.findPetType(pet.getTypeId()), this.visits.findByPetId(pet.getId())))
						.collect(Collectors.toList()));
		return mav;
	}

	public final class OwnerDetails {

		private final Owner owner;

		private final List<Pet> pets;

		public OwnerDetails(Owner owner, List<Pet> pets) {
			this.owner = owner;
			this.pets = pets;
		}

		public Owner getOwner() {
			return owner;
		}

		public List<Pet> getPets() {
			return pets;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null || obj.getClass() != this.getClass()) return false;
			OwnerDetails that = (OwnerDetails) obj;
			return Objects.equals(this.owner, that.owner) &&
					Objects.equals(this.pets, that.pets);
		}

		@Override
		public int hashCode() {
			return Objects.hash(owner, pets);
		}

		@Override
		public String toString() {
			return "OwnerDetails[" +
					"owner=" + owner + ", " +
					"pets=" + pets + ']';
		}

		}

	public final class PetDetails {

		private final Pet pet;

		private final PetType type;

		private final List<Visit> visits;

		public PetDetails(Pet pet, PetType type, List<Visit> visits) {
			this.pet = pet;
			this.type = type;
			this.visits = visits;
		}

		public Pet getPet() {
			return pet;
		}

		public PetType getType() {
			return type;
		}

		public List<Visit> getVisits() {
			return visits;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null || obj.getClass() != this.getClass()) return false;
			PetDetails that = (PetDetails) obj;
			return Objects.equals(this.pet, that.pet) &&
					Objects.equals(this.type, that.type) &&
					Objects.equals(this.visits, that.visits);
		}

		@Override
		public int hashCode() {
			return Objects.hash(pet, type, visits);
		}

		@Override
		public String toString() {
			return "PetDetails[" +
					"pet=" + pet + ", " +
					"type=" + type + ", " +
					"visits=" + visits + ']';
		}

		}

}
