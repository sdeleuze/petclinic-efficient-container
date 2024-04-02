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

import java.util.Objects; /**
 * Reference between {@link Vet} and {@link Specialty} required to have many to many relationships in Spring Data JDBC.
 */
public final class VetSpecialty {
	private final Long specialty;

	private final Long vet;

	public VetSpecialty(Long specialty, Long vet) {
		this.specialty = specialty;
		this.vet = vet;
	}

	public Long specialty() {
		return specialty;
	}

	public Long vet() {
		return vet;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		VetSpecialty that = (VetSpecialty) obj;
		return Objects.equals(this.specialty, that.specialty) &&
				Objects.equals(this.vet, that.vet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(specialty, vet);
	}

	@Override
	public String toString() {
		return "VetSpecialty[" +
				"specialty=" + specialty + ", " +
				"vet=" + vet + ']';
	}

}
