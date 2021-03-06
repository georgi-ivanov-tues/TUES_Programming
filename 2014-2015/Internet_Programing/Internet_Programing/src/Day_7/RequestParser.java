package Day_7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

	public static Request parse(List<String> request) {
		String[] firstLineParsed = request.remove(0).split(" ");

		Map<String, String> headers = new HashMap<>();
		for (String header : request) {
			String[] secondLine = header.split(": ");
			headers.put(secondLine[0], secondLine[1]);
		}

		return new Request(firstLineParsed[0],
				firstLineParsed[1], firstLineParsed[2], headers, null);
	}
}

/*
Domashno: sazdavame RequsetParser v server
Puskame parse
Vzimame request-a
ako e GET printirame
ina4e 404

+ nqkvo si ese...
*/
