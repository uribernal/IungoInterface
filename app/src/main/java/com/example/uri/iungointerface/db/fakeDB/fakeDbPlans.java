package com.example.uri.iungointerface.db.fakeDB;

import com.example.uri.iungointerface.db.Plan;
import com.example.uri.iungointerface.db.User;

import java.util.ArrayList;

/**
 * Created by Uri on 20/09/2017.
 */

public class fakeDbPlans {

    private ArrayList<Plan> plans;

    public fakeDbPlans() {
        createPlans();
    }

    private void createPlans() {
        fakeDbUsers fDbU = new fakeDbUsers();

        plans = new ArrayList<Plan>();
        ArrayList<String> usersIds1 = new ArrayList<String>();
        usersIds1.add("0");
        ArrayList<String> usersIds2 = new ArrayList<String>();
        usersIds2.add("0");
        usersIds2.add("1");
        ArrayList<String> usersIds3 = new ArrayList<String>();
        usersIds3.add("1");
        usersIds3.add("2");
        usersIds3.add("3");
        ArrayList<String> usersIds4 = new ArrayList<String>();
        usersIds4.add("0");
        usersIds4.add("1");
        usersIds4.add("2");
        usersIds4.add("3");
        usersIds4.add("4");
        usersIds4.add("5");
        ArrayList<String> usersIds5 = new ArrayList<String>();

        ArrayList<String> planPics1 = new ArrayList<String>();
        planPics1.add("https://cdns3.eltiempo.es/eltiempo/blog/noticias/2016/05/windsurf.jpeg");
        planPics1.add("http://www.lovevalencia.com/wp-content/uploads/2016/08/Windsurf-en-Valencia.jpg");
        ArrayList<String> planPics2 = new ArrayList<String>();
        planPics2.add("http://getbg.net/upload/full/241901_skejtbordist_sport_adrenalin_3840x1080_(www.GetBg.net).jpg");
        planPics2.add("http://jovenes.guanajuato.gob.mx/wp-content/uploads/bfi_thumb/Thumbnail-Lugares-para-andar-de-Skate-30goij6tv0ogfgbhogr7r4.jpg");
        planPics2.add("https://images.alphacoders.com/268/thumb-1920-268351.jpg");
        planPics2.add("https://images6.alphacoders.com/423/423562.png");
        ArrayList<String> planPics3 = new ArrayList<String>();
        planPics3.add("http://www.grindtv.com/wp-content/uploads/2014/11/MPM-TOMASZ_KAYAK_6.jpg");
        planPics3.add("https://s-media-cache-ak0.pinimg.com/736x/46/d6/41/46d64170fb01fd3149a990980a49e541.jpg");
        planPics3.add("http://www.kayaksport.net/media/slider/slider1.jpg");
        planPics3.add("http://i.dailymail.co.uk/i/pix/2014/11/20/2352CF1C00000578-2842164-Tomasz_mainly_uses_a_helmet_mounted_GOPRO3_camera_to_capture_the-27_1416483274796.jpg");
        ArrayList<String> planPics4 = new ArrayList<String>();
        planPics4.add("http://cdn.paper4pc.com/images/surfer-girls-wallpaper-7.jpg");
        planPics4.add("http://static1.objetivobienestar.com/uploads/s1/37/31/5/surf-deporte-actitud-37315.jpg");
        planPics4.add("http://www.grindtv.com/wp-content/uploads/2010/05/113_pat_5141-copy.jpg");
        ArrayList<String> planPics5 = new ArrayList<String>();
        planPics5.add("http://www.huelvaesdeporte.com/sites/default/files/img_cuales_son_los_tipos_de_escalada_mas_comunes_18948_orig.jpg");
        planPics5.add("http://deportes-aventura.es/wp-content/uploads/2013/05/escalada1.jpg");
        planPics5.add("https://montagnamontagna.files.wordpress.com/2012/11/elias-gonzalez-escalada-covachon-gradura-teverga-asturias.jpg");
        ArrayList<String> planPics6 = new ArrayList<String>();
        planPics6.add("http://www.peaceinthecaucasus.org/wp-content/uploads/2017/02/sushi.jpg");
        planPics6.add("http://www.makesushi.com/wp-content/uploads/2015/02/mosaic-sushi-roll-evolution.jpg");
        planPics6.add("http://www.hiresushichef.co.za/images/SushiClass.jpg");
        ArrayList<String> planPics7 = new ArrayList<String>();
        planPics7.add("https://www.menshealth.com/sites/menshealth.com/files/yoga-saves-mans-life.jpg");
        planPics7.add("https://static1.squarespace.com/static/560578ece4b090be93f4b477/t/56058327e4b0252146bd1316/1443201831556/yoga2.jpg?format=1000w");
        planPics7.add("http://www.chopra.com/sites/default/files/field/image/ManDoingYoga_0.jpg");
        planPics7.add("https://www.kamalaya.com/fileadmin/user_upload/WELLNESS-PROGRAMS-THAILAND/yoga-thailand-2000x850/yoga-retreats-thailand.jpg");
        ArrayList<String> planPics8 = new ArrayList<String>();
        planPics8.add("http://spartacusbubblesoccer.co.uk/wp-content/uploads/2016/12/bubble-football-prices.jpg");
        planPics8.add("http://www.bubble-football.co.uk/s/img/emotionheader.jpg?1502803189.774px.375px");
        planPics8.add("https://images.gogroopie.com/i/v5_f93ex0f5w_2.jpg");

        Plan a = new Plan("0", "Windsurf", "50", "Windsurf rules!", "c/Urquinaona 2", "c/Balmes 370", "3/3/2018", "10:00", 1, 1, 10, usersIds1, planPics1);
        plans.add(a);

        a = new Plan("1", "Skate", "25", "Come to skate in Barcelona with your friends!", "c/Mitre 370", "c/Pelayo 2", "5/3/2018", "10:00", 2, 10, 2, usersIds2, planPics2);
        plans.add(a);

        a = new Plan("2", "Kayak", "100", "Alfred marica", "Cuenca", "Cuenca2", "5/3/2018", "16:00", 3, 3, 3, usersIds3, planPics3);
        plans.add(a);

        a = new Plan("3", "Surfing", "120", "caca", "barcelona", "pl catalunya", "8/3/2018", "11:00", 6, 6, 2, usersIds4, planPics4);
        plans.add(a);

        a = new Plan("4", "Climbing", "20", "escalada!!", "Masnou", "Ocata", "12/3/2018", "9:00", 0, 2, 1, usersIds5, planPics5);
        plans.add(a);

        a = new Plan("5", "Sushi classes", "20", "cooking sushi!!", "Masnou", "Ocata", "12/3/2018", "9:00", 0, 2, 1, usersIds5, planPics6);
        plans.add(a);

        a = new Plan("6", "Yoga", "20", "yoga!!", "Masnou", "Ocata", "12/3/2018", "9:00", 0, 2, 1, usersIds5, planPics7);
        plans.add(a);

        a = new Plan("7", "Bubble football", "20", "enjoy!!", "Masnou", "Ocata", "12/3/2018", "9:00", 0, 2, 1, usersIds5, planPics8);
        plans.add(a);

    }

    public ArrayList<Plan> getPlans(){

        return plans;
    }

    public Plan getPlan(String id) {
        return plans.get(Integer.parseInt(id));

    }

    public ArrayList<Plan> getPlans(ArrayList<String> ids) {
        ArrayList<Plan> p = new ArrayList<Plan>();

        for (String id: ids){
            p.add(getPlan(id));
        }
        return p;
    }

}
