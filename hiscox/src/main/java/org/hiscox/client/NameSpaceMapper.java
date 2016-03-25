package org.hiscox.client;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;


public class NameSpaceMapper extends NamespacePrefixMapper {
	private static final String FOO_PREFIX = ""; // DEFAULT NAMESPACE
    private static final String FOO_URI = "http://schemas.datacontract.org/2004/07/RKLServiceQueue";


    /*
     * (non-Javadoc)
     * 
     * @see com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper#getPreferredPrefix(java.lang.String,
     * java.lang.String, boolean)
     */
    @Override
    public String getPreferredPrefix(final String pNameSpaceUri, final String pSuggestion, final boolean pRequirePrefix)
    {
       

        return pSuggestion;
    }
    
}