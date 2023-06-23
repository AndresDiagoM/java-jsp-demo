package com.mycompany.spacex.persistence.entities;

import com.mycompany.spacex.persistence.entities.Missions;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-06-23T01:20:54")
@StaticMetamodel(Payloads.class)
public class Payloads_ { 

    public static volatile SingularAttribute<Payloads, String> payloadId;
    public static volatile SingularAttribute<Payloads, String> manufacture;
    public static volatile SingularAttribute<Payloads, String> reuse;
    public static volatile SingularAttribute<Payloads, String> regime;
    public static volatile SingularAttribute<Payloads, Float> massLb;
    public static volatile SingularAttribute<Payloads, String> name;
    public static volatile SingularAttribute<Payloads, String> orbit;
    public static volatile SingularAttribute<Payloads, String> type;
    public static volatile ListAttribute<Payloads, Missions> missionsList;
    public static volatile SingularAttribute<Payloads, Float> massKg;
    public static volatile SingularAttribute<Payloads, String> referenceSystem;

}