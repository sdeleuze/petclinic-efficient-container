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

import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for the 'vets'
 * {@link MarshallingView}.
 */
public final class Vets {

	private final List<VetDto> vets;

	public Vets(List<VetDto> vets) {
		this.vets = vets;
	}

	public List<VetDto> getVets() {
		return vets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		Vets that = (Vets) obj;
		return Objects.equals(this.vets, that.vets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vets);
	}

	@Override
	public String toString() {
		return "Vets[" +
				"vets=" + vets + ']';
	}

}
