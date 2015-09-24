/**
 * e-commerce-rest-java
 *
 * Copyright 2015 juanitodread
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
 *
 */
package org.juanitodread.ecommercerest.model.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple POJO to represent an hypermedia link.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 23, 2015
 */
@XmlRootElement( name = "link" )
public class Link implements Serializable {

    private static final long serialVersionUID = -3871240546240243087L;
    
    private static Pattern parse = Pattern.compile( "<(.+)>\\s*;\\s*(.+)" );

    @XmlAttribute( name = "rel" )
    private String relationship = "";
    @XmlAttribute
    private String href = "";
    @XmlAttribute
    private String type = "";
    
    public Link( ) {
    }
    
    /**
     * @param relationship
     * @param href
     * @param type
     */
    public Link( String relationship,
                 String href,
                 String type ) {
        this.relationship = relationship;
        this.href = href;
        this.type = type;
    }

    /**
     * @return the relationship
     */
    public String getRelationship( ) {
        return relationship;
    }

    /**
     * @return the href
     */
    public String getHref( ) {
        return href;
    }

    /**
     * @return the type
     */
    public String getType( ) {
        return type;
    }
    
    public static Link valueOf( String val ) {
        Matcher matcher = parse.matcher( val );
        if( !matcher.matches( ) ) {
            throw new RuntimeException( String.format( "Failed to parse link: %s",
                                                       val ) );
        }
        
        Link link = new Link( );
        link.href = matcher.group( 1 );
        
        String [ ] props = matcher.group( 2 ).split( ";" );
        String [ ] prop = null;
        Map<String, String> map = new HashMap<>( props.length );
        for( String propStr: props ) {
            prop = propStr.split( "=" );
            map.put( prop[0].trim( ), prop[1].trim( ) );
        }

        // setting the other properties
        if( map.containsKey( "rel" ) ) {
            link.relationship = map.get( "rel" );
        }
        
        if( map.containsKey( "type" ) ) {
            link.type = map.get( "type" );
        }
        
        return link;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString( ) {
        return String.format( "<%s>; rel=%s; type=%s",
                href,
                relationship,
                type );
    }
    
}
