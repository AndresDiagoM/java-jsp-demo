package com.mycompany.spacex.persistence.entities;

import com.mycompany.spacex.persistence.entities.Launchpads;
import com.mycompany.spacex.persistence.entities.Payloads;
import com.mycompany.spacex.persistence.entities.Rockets;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-23T01:20:54")
@StaticMetamodel(Missions.class)
public class Missions_ { 

    public static volatile SingularAttribute<Missions, Date> date;
    public static volatile SingularAttribute<Missions, Payloads> payloadId;
    public static volatile SingularAttribute<Missions, String> launchId;
    public static volatile SingularAttribute<Missions, Rockets> rocketId;
    public static volatile SingularAttribute<Missions, String> launchStatus;
    public static volatile SingularAttribute<Missions, String> name;
    public static volatile SingularAttribute<Missions, Launchpads> launchpadId;

}