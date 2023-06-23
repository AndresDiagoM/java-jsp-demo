package com.mycompany.spacex.persistence.entities;

import com.mycompany.spacex.persistence.entities.Missions;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-23T01:20:54")
@StaticMetamodel(Launchpads.class)
public class Launchpads_ { 

    public static volatile SingularAttribute<Launchpads, Float> latitude;
    public static volatile SingularAttribute<Launchpads, String> name;
    public static volatile SingularAttribute<Launchpads, String> locality;
    public static volatile SingularAttribute<Launchpads, String> fullName;
    public static volatile SingularAttribute<Launchpads, String> timeZone;
    public static volatile SingularAttribute<Launchpads, String> launchpadId;
    public static volatile SingularAttribute<Launchpads, String> region;
    public static volatile ListAttribute<Launchpads, Missions> missionsList;
    public static volatile SingularAttribute<Launchpads, String> status;
    public static volatile SingularAttribute<Launchpads, Float> longitude;

}