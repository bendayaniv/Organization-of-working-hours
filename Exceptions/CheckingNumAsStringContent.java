package NadavOren_YanivBenDavid.Exceptions;

public class CheckingNumAsStringContent {
	public CheckingNumAsStringContent(String id, String type, int numType) throws OurException {
		boolean numBeforeFloatPoint = false;
		boolean numAfterFloatPoint = false;
		boolean floatPointAppered = false;
		for (int i = 0; i < id.length(); i++) {
			if (numType == 2 && id.charAt(i) == '.') {
				if(!numBeforeFloatPoint)
					throw new OurException("Error, there is no number before the float point");	
				else if(floatPointAppered)
					throw new OurException("Error, can't have more then one float point");
				else
					floatPointAppered = true;
					continue;
			}
			else if (!Character.isDigit(id.charAt(i))) {
				throw new OurException("Error, not all characters of " + type + " are digits.\nTry again");
			}
			else if (numType == 2 && Character.isDigit(id.charAt(i)) ) {
				if(!numBeforeFloatPoint)
					numBeforeFloatPoint = true;
				if(floatPointAppered)
					numAfterFloatPoint = true;
			}
		}
		if(numType == 2 && !numAfterFloatPoint && floatPointAppered )
			throw new OurException("Error, need to have a digit after the float point");
	}
}
