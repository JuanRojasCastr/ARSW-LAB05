package edu.eci.arsw.blueprints.service;

import edu.eci.arsw.blueprints.service.model.Blueprint;
import edu.eci.arsw.blueprints.service.model.Point;
import edu.eci.arsw.blueprints.service.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.service.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices  bps = ac.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10), new Point(10, 10),new Point(11, 10), new Point(15, 10), new Point(15, 10), new Point(0,0)};
        bps.addNewBlueprint(new Blueprint("Pedro","casa#38", pts));
        bps.addNewBlueprint(new Blueprint("Pedro","casa#39"));
        bps.addNewBlueprint(new Blueprint("Juan","casa#12"));
        bps.addNewBlueprint(new Blueprint("Pedro","casa#8"));
        try {
            Set<Blueprint> authorBP = bps.getBlueprintsByAuthor("Pedro");
            System.out.println("get Por Autor: pedro");
            for (Blueprint b: authorBP) {
                System.out.println(b);
            }
            System.out.println("------Normal get-----");
            System.out.println(bps.getBlueprint("Juan", "casa#12"));
        } catch (BlueprintNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}