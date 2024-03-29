/* XML Im-/Exporter: Copyright 2002-2004, Oliver Zeigermann (oliver@zeigermann.de)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this list of
 *   conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list
 *   of conditions and the following disclaimer in the documentation and/or other materials
 *   provided with the distribution.
 * - Neither the name of the Oliver Zeigermann nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without specific
 *   prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, ORTORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */ 
package wsl.fw.xml.simpleImporter;

import org.xml.sax.helpers.AttributesImpl;

/**
 * Callback interface for {@link SimpleImporter}. 
 *
 * @author <a href="mailto:oliver@zeigermann.de">Olli Z.</a>
 */
public interface SimpleImportHandler {
    
    /** Is called back when the parsed document begins. */
    public void startDocument();

    /** Is called back when the parsed document ends. */
    public void endDocument();
    
    /** 
     * Is called back when the parser has found character data.
     * <br>
     * <em>Caution:</em> 
     * This method will not be called when 
     * SimpleImporter#setIncludeLeadingCDataIntoStartElementCallback(boolean)
     * is enabled. In this case character data will
     * be passed over together with {@link #startElement(SimplePath, String, AttributesImpl, String)}.
     * <br>
     * Unlike the character method in the SAX interface this callback guarantees
     * maximum length chunks of character data. This means, on a contiguous text 
     * block, i.e. text not intermitted by tagging, you will get exactly one
     * callback.  
     * 
     * @see #startElement(SimplePath, String, AttributesImpl, String)
     * 
     * @param path path of the element closed by this end tag
     * @param cdata The character data (like in SAX, but unlike from the
     * {@link #startElement(SimplePath, String, AttributesImpl, String)} call a sequence of CDATA is not
     * guaranteed to be grouped together into one callback)
     * of this callbacks. If leading CDATA is delivered together with 
     * {@link #startElement(SimplePath, String, AttributesImpl, String)} it will not be called back here.
     */
    public void cData(SimplePath path, String cdata);
    
    /** 
     * Is called back when the parser has found the start of an element. 
     *
     * This callback is especially convenient when your data does not have
     * mixed content, i.e. the mixture of CDATA and tagging in one element 
     * level. When this is the case you will always get the whole text content
     * together with this callback in the <code>leadingCDdata</code> parameter.
     * Unlike from {@link #cData(SimplePath, String)} callback all character data fragments will 
     * be grouped together in this parameter.<br>
     *
     * If you have to deal with mixed content you can still leave this feature
     * enabled, but you will have to be aware of the fact that you will then 
     * get some character data via this callback and other via the 
     * {@link #cData(SimplePath, String)} callback.
     *
     * @param path path of the element closed by this end tag
     * @param name the name of the start tag
     * @param attritues SAX attributes associated to this element
     * @param leadingCDdata If enabled in 
     * {@link SimpleImporter#setIncludeLeadingCDataIntoStartElementCallback(boolean)} 
     * the text directly following the start tag, i.e. before any 
     * other tagging. If this is enabled you will <em>not</em> get this text 
     * via the {@link #cData(SimplePath, String)} callback.
     */
    public void startElement(SimplePath path, String name, AttributesImpl attributes, String leadingCDdata);

    /** 
     * Is called back when the parser has found the end of an element. 
     * @param path path of the element closed by this end tag
     * @param name the name of the element to be closed
     */
    public void endElement(SimplePath path, String name);
}
