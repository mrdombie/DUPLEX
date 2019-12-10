package com.dom.duplex.client.transformer;

import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.api.DataApiUser;

public final class UserApiTransformer {

	private UserApiTransformer() {
		// empty by design
	}

	/**
	 * Transform a {@link CsvEntry} to a {@link CsvEntry}
	 *
	 * @param entry
	 * @return {@link DataApiUser}
	 */

	public static DataApiUser toApiUser(final CsvEntry entry) {
		return new DataApiUser().setAge(entry.getAge()).setHeight(entry.getHeight()).setName(entry.getName());
	}
}
