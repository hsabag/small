package org.sb.lightadmin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;
import org.sb.persist.data.CompanyEntity;

public class CompanyAdministration extends AdministrationConfiguration<CompanyEntity> {
	 
public EntityMetadataConfigurationUnit configuration( EntityMetadataConfigurationUnitBuilder configurationBuilder ) {
 return configurationBuilder.nameField( "firstname" ).build();
 }
 
public ScreenContextConfigurationUnit screenContext( ScreenContextConfigurationUnitBuilder screenContextBuilder ) {
 return screenContextBuilder
 .screenName( "Users Administration" ).build();
 }
 
public FieldSetConfigurationUnit listView( final FieldSetConfigurationUnitBuilder fragmentBuilder ) {
 return fragmentBuilder
 .field( "firstname" ).caption( "First Name" )
 .field( "lastname" ).caption( "Last Name" )
 .build();
 }
}
