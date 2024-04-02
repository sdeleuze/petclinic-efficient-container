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
package org.springframework.samples.petclinic.vet;

import java.util.List;
import java.util.Objects;

public final class VetDto {

	private final Long id;

	private final String firstName;

	private final String lastName;

	private final List<Specialty> specialties;

	public VetDto(Long id, String firstName, String lastName, List<Specialty> specialties) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialties = specialties;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public int getNrOfSpecialties() {
		return specialties.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		VetDto that = (VetDto) obj;
		return Objects.equals(this.id, that.id) &&
				Objects.equals(this.firstName, that.firstName) &&
				Objects.equals(this.lastName, that.lastName) &&
				Objects.equals(this.specialties, that.specialties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, specialties);
	}

	@Override
	public String toString() {
		return "VetDto[" +
				"id=" + id + ", " +
				"firstName=" + firstName + ", " +
				"lastName=" + lastName + ", " +
				"specialties=" + specialties + ']';
	}

}
