/**
 * Copyright 2015 Alex Yanchenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.droidparts.inner.converter;

import org.droidparts.inner.TypeHelper;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class BooleanConverter extends Converter<Boolean> {

	@Override
	public boolean canHandle(Class<?> cls) {
		return TypeHelper.isBoolean(cls, true);
	}

	@Override
	public String getDBColumnType() {
		return INTEGER;
	}

	@Override
	public <G1, G2> Boolean readFromJSON(Class<Boolean> valType, Class<G1> genericType1, Class<G2> genericType2,
			JSONObject obj, String key) throws Exception {
		try {
			return obj.getBoolean(key);
		} catch (Exception e) {
			return parseFromString(valType, genericType1, genericType2, obj.getString(key));
		}
	}

	@Override
	protected <G1, G2> Boolean parseFromString(Class<Boolean> valType, Class<G1> genericType1, Class<G2> genericType2,
			String str) {
		if ("1".equals(str)) {
			str = "true";
		}
		return Boolean.valueOf(str);
	}

	@Override
	public <G1, G2> void putToContentValues(Class<Boolean> valueType, Class<G1> genericType1, Class<G2> genericType2,
			ContentValues cv, String key, Boolean val) {
		cv.put(key, val);
	}

	@Override
	public <G1, G2> Boolean readFromCursor(Class<Boolean> valType, Class<G1> genericType1, Class<G2> genericType2,
			Cursor cursor, int columnIndex) {
		return (cursor.getInt(columnIndex) == 1);
	}

}
