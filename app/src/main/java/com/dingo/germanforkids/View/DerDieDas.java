package com.dingo.germanforkids.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityDerDieDasBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class DerDieDas extends AppCompatActivity implements View.OnClickListener {
    private String[] article;
    private ArrayList<String> articleList;
    private boolean checkCorrectAns = true;
    private int correct_answer;
    private AlertDialog dialog;
    private int[] image;
    private ArrayList<Integer> imagesList;
    private ImageView imgNext;
    private ImageView imgQue;
   

    private MediaPlayer mediaPlayer;
    private String name;
    private boolean noAds;
    private int position;
    private int[] sound;
    private ArrayList<Integer> soundsList;
    private TextView txtArticle;
    private TextView txtDas;
    private TextView txtDer;
    private TextView txtDie;
    private TextView txtWord;
    private String[] word;
    private ArrayList<String> wordsList;

    ActivityDerDieDasBinding binding;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityDerDieDasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readSharedPrefForAdsAndMoreSub();
        initComponents();
        lessonData();
        addItems();
        binding.imgBacks.setOnClickListener(this);
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.imgQue);

    }

    private void readSharedPrefForAdsAndMoreSub() {
        noAds = getSharedPreferences("store", 0).getBoolean("removeAds", false);
    }

    private void initComponents() {
        imgQue = (ImageView) findViewById(R.id.imgQue);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        txtWord = (TextView) findViewById(R.id.txtWord);
        txtArticle = (TextView) findViewById(R.id.txtArticle);
        txtDer = (TextView) findViewById(R.id.txtDer);
        txtDie = (TextView) findViewById(R.id.txtDie);
        txtDas = (TextView) findViewById(R.id.txtDas);
        wordsList = new ArrayList<>();
        articleList = new ArrayList<>();
        soundsList = new ArrayList<>();
        imagesList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }
        MediaPlayer create = MediaPlayer.create(getApplicationContext(), (int) R.raw.mediaplayer_start);
        mediaPlayer = create;
        create.start();

        binding.txtTittle.setText(name);
    }

    private void speak(int i) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer = null;
            MediaPlayer create = MediaPlayer.create(getApplicationContext(), i);
            mediaPlayer = create;
            create.start();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { 
            @Override 
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
            }
        });
    }

    public void lessonData() {
        if (name.equalsIgnoreCase("Formen")) {
            word = new String[]{"Kreis", "Quadrat", "Dreieck", "Rechteck", "Oval", "Herz", "Diamant", "Stern", "Sechseck"};
            article = new String[]{"der", "das", "das", "das", "das", "das", "der", "der", "das"};
            sound = new int[]{R.raw.circle, R.raw.square, R.raw.triangle, R.raw.rectangle, R.raw.oval, R.raw.heart, R.raw.diamond, R.raw.star, R.raw.hexagon};
            image = new int[]{R.drawable.circle, R.drawable.square, R.drawable.triangle, R.drawable.rectangle, R.drawable.oval, R.drawable.heart, R.drawable.diamond, R.drawable.star, R.drawable.hexagon};
        } else if (name.equalsIgnoreCase("Wochentage")) {
            word = new String[]{"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
            article = new String[]{"der", "der", "der", "der", "der", "der", "der"};
            sound = new int[]{R.raw.monday, R.raw.tuesday, R.raw.wednesday, R.raw.thursday, R.raw.friday, R.raw.saturday, R.raw.sunday};
            image = new int[]{R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday, R.drawable.thursday, R.drawable.friday, R.drawable.saturday, R.drawable.sunday};
        } else if (name.equalsIgnoreCase("Obst")) {
            word = new String[]{"Wassermelone", "Banane", "Traube", "Pfirsich", "Grapefruit", "Zitrone", "Mandarine", "Erdbeere", "Feige", "Kiwi", "Apfel", "Pflaume", "Birne", "Orange", "Melone", "Ananas", "Granatapfel", "Aprikose", "Kirsche"};
            article = new String[]{"die", "die", "die", "der", "die", "die", "die", "die", "die", "die", "der", "die", "die", "die", "die", "die", "der", "die", "die"};
            sound = new int[]{R.raw.watermelon, R.raw.banana, R.raw.grapes, R.raw.peach, R.raw.grapefruit, R.raw.lemon, R.raw.tangerine, R.raw.strawberry, R.raw.fig, R.raw.kiwi, R.raw.apple, R.raw.plum, R.raw.pear, R.raw.orange_fruit, R.raw.melon, R.raw.pineapple, R.raw.pomegranate, R.raw.apricot, R.raw.cherry};
            image = new int[]{R.drawable.watermelon, R.drawable.banana, R.drawable.grapes, R.drawable.peach, R.drawable.grapefruit, R.drawable.lemon, R.drawable.tangerine, R.drawable.strawberry, R.drawable.fig, R.drawable.kiwi, R.drawable.apple, R.drawable.plum, R.drawable.pear, R.drawable.orange_fruit, R.drawable.melon, R.drawable.pineapple, R.drawable.pomegranate, R.drawable.apricot, R.drawable.cherry};
        } else if (name.equalsIgnoreCase("Gemüse")) {
            word = new String[]{"Karotte", "Knoblauch", "Kartoffel", "Tomate", "Gurke", "Mais", "Paprika", "Kohl", "Blumenkohl", "Zwiebel", "Kopfsalat", "Aubergine", "Lauch", "Okra"};
            article = new String[]{"die", "der", "die", "die", "die", "der", "die", "der", "der", "die", "der", "die", "der", "die"};
            sound = new int[]{R.raw.carrot, R.raw.garlic, R.raw.potato, R.raw.tomato, R.raw.cucumber, R.raw.corn, R.raw.bell_pepper, R.raw.cabbage, R.raw.cauliflower, R.raw.onion, R.raw.lettuce, R.raw.eggplant, R.raw.leek, R.raw.okra};
            image = new int[]{R.drawable.carrot, R.drawable.garlic, R.drawable.potato, R.drawable.tomato, R.drawable.cucumber, R.drawable.corn, R.drawable.bell_pepper, R.drawable.cabbage, R.drawable.cauliflower, R.drawable.onion, R.drawable.lettuce, R.drawable.eggplant, R.drawable.leek, R.drawable.okra};
        } else if (name.equalsIgnoreCase("Tiere")) {
            word = new String[]{"Pferd", "Esel", "Kamel", "Kuh", "Schwein", "Schaf", "Ziege", "Katze", "Hund", "Kaninchen", "Maus", "Löwe", "Tiger", "Giraffe", "Elefant", "Bär", "Zebra", "Affe", "Panda", "Krokodil", "Schlange", "Schildkröte"};
            article = new String[]{"das", "der", "das", "die", "das", "das", "die", "die", "der", "das", "die", "der", "der", "die", "der", "der", "das", "der", "der", "das", "die", "die"};
            sound = new int[]{R.raw.horse, R.raw.donkey, R.raw.camel, R.raw.cow, R.raw.pig, R.raw.sheep, R.raw.goat, R.raw.cat, R.raw.dog, R.raw.rabbit, R.raw.mouse, R.raw.lion, R.raw.tiger, R.raw.giraffe, R.raw.elephant, R.raw.bear, R.raw.zebra, R.raw.monkey, R.raw.panda, R.raw.crocodile, R.raw.snake, R.raw.tortoise};
            image = new int[]{R.drawable.horse, R.drawable.donkey, R.drawable.camel, R.drawable.cow, R.drawable.pig, R.drawable.sheep, R.drawable.goat, R.drawable.cat, R.drawable.dog, R.drawable.rabbit, R.drawable.mouse, R.drawable.lion, R.drawable.tiger, R.drawable.giraffe, R.drawable.elephant, R.drawable.bear, R.drawable.zebra, R.drawable.monkey, R.drawable.panda, R.drawable.crocodile, R.drawable.snake, R.drawable.tortoise};
        } else if (name.equalsIgnoreCase("Vögel")) {
            word = new String[]{"Huhn", "Hahn", "Ente", "Gans", "Pute", "Taube", "Adler", "Eule", "Papagei", "Strauß", "Pinguin"};
            article = new String[]{"das", "der", "die", "die", "die", "die", "der", "die", "der", "der", "der"};
            sound = new int[]{R.raw.chicken, R.raw.rooster, R.raw.duck, R.raw.goose, R.raw.turkey, R.raw.pigeon, R.raw.eagle, R.raw.owl, R.raw.parrot, R.raw.ostrich, R.raw.penguin};
            image = new int[]{R.drawable.chicken, R.drawable.rooster, R.drawable.duck, R.drawable.goose, R.drawable.turkey, R.drawable.pigeon, R.drawable.eagle, R.drawable.owl, R.drawable.parrot, R.drawable.ostrich, R.drawable.penguin};
        } else if (name.equalsIgnoreCase("Essen")) {
            word = new String[]{"Pizza", "Hamburger", "Brot", "Hot Dog", "Ei", "Salat", "Käse", "Fisch", "Reis", "Suppe", "Hähnchen", "Eis", "Pfannkuchen", "Kuchen", "Schokolade", "Saft", "Milch", "Wasser"};
            article = new String[]{"die", "der", "das", "der", "das", "der", "der", "der", "der", "die", "das", "das", "der", "der", "die", "der", "die", "das"};
            sound = new int[]{R.raw.pizza, R.raw.hamburger, R.raw.bread, R.raw.hot_dog, R.raw.egg, R.raw.salad, R.raw.cheese, R.raw.fish, R.raw.rice, R.raw.soup, R.raw.chicken, R.raw.ice_cream, R.raw.pancake, R.raw.cake, R.raw.chocolate, R.raw.juice, R.raw.milk, R.raw.water};
            image = new int[]{R.drawable.pizza, R.drawable.hamburger, R.drawable.bread, R.drawable.hot_dog, R.drawable.egg, R.drawable.salad, R.drawable.cheese, R.drawable.fish, R.drawable.rice, R.drawable.soup, R.drawable.chicken_food, R.drawable.ice_cream, R.drawable.pancake, R.drawable.cake, R.drawable.chocolate, R.drawable.juice, R.drawable.milk, R.drawable.water};
        } else if (name.equalsIgnoreCase("Kleidung")) {
            word = new String[]{"Jeans", "Bluse", "Rock", "Pullover", "Hose", "Kleid", "Jacke", "Weste", "Mantel", "Kappe", "Hut", "Handschuhe", "Schal", "Stiefel", "Turnschuhe", "Schuhe", "Tasche", "T-Shirt", "Hemd", "Gürtel", "Krawatte", "Socken"};
            article = new String[]{"die", "die", "der", "der", "die", "das", "die", "die", "der", "die", "der", "die", "der", "der", "die", "die", "die", "das", "das", "der", "die", "die"};
            sound = new int[]{R.raw.jeans, R.raw.blouse, R.raw.skirt, R.raw.sweater, R.raw.pants, R.raw.dress, R.raw.jacket, R.raw.vest, R.raw.coat, R.raw.cap, R.raw.hat, R.raw.gloves, R.raw.scarf, R.raw.boots, R.raw.sneakers, R.raw.shoes, R.raw.handbag, R.raw.tshirt, R.raw.shirt, R.raw.belt, R.raw.tie, R.raw.socks};
            image = new int[]{R.drawable.jeans, R.drawable.blouse, R.drawable.skirt, R.drawable.sweater, R.drawable.pants, R.drawable.dress, R.drawable.jacket, R.drawable.vest, R.drawable.coat, R.drawable.cap, R.drawable.hat, R.drawable.gloves, R.drawable.scarf, R.drawable.boots, R.drawable.sneakers, R.drawable.shoes, R.drawable.handbag, R.drawable.tshirt, R.drawable.shirt, R.drawable.belt, R.drawable.tie, R.drawable.socks};
        } else if (name.equalsIgnoreCase("Küche")) {
            word = new String[]{"Kühlschrank", "Herd", "Wasserkocher", "Messer", "Löffel", "Gabel", "Reibe", "Bratpfanne", "Topf", "Glas", "Tasse", "Teller", "Schüssel", "Schöpfkelle", "Flasche"};
            article = new String[]{"der", "der", "der", "das", "der", "die", "die", "die", "der", "das", "die", "der", "die", "der", "die"};
            sound = new int[]{R.raw.refrigerator, R.raw.oven, R.raw.kettle, R.raw.knife, R.raw.spoon, R.raw.fork, R.raw.grater, R.raw.frying_pan, R.raw.pot, R.raw.glass, R.raw.cup, R.raw.plate, R.raw.bowl, R.raw.ladle, R.raw.bottle};
            image = new int[]{R.drawable.refrigerator, R.drawable.oven, R.drawable.kettle, R.drawable.knife, R.drawable.spoon, R.drawable.fork, R.drawable.grater, R.drawable.frying_pan, R.drawable.pot, R.drawable.glass, R.drawable.cup, R.drawable.plate, R.drawable.bowl, R.drawable.ladle, R.drawable.bottle};
        } else if (name.equalsIgnoreCase("Badezimmer")) {
            word = new String[]{"Badewanne", "Dusche", "Waschbecken", "Spiegel", "Handtuch", "Toilette", "Seife", "Toilettenpapier", "Zahnbürste", "Zahnpasta", "Shampoo", "Föhn"};
            article = new String[]{"die", "die", "das", "der", "das", "die", "die", "das", "die", "die", "das", "der"};
            sound = new int[]{R.raw.bathtub, R.raw.shower, R.raw.sink, R.raw.mirror, R.raw.towel, R.raw.toilet, R.raw.soap, R.raw.toilet_paper, R.raw.toothbrush, R.raw.toothpaste, R.raw.shampoo, R.raw.hairdryer};
            image = new int[]{R.drawable.bathtub, R.drawable.shower, R.drawable.sink, R.drawable.mirror, R.drawable.towel, R.drawable.toilet, R.drawable.soap, R.drawable.toilet_paper, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.shampoo, R.drawable.hairdryer};
        } else if (name.equalsIgnoreCase("Wohnzimmer")) {
            word = new String[]{"Telefon", "Sofa", "Sessel", "Fernseher", "Tisch", "Lampe", "Teppich", "Kronleuchter", "Kamin", "Uhr", "Vase"};
            article = new String[]{"das", "das", "der", "der", "der", "die", "der", "der", "der", "die", "die"};
            sound = new int[]{R.raw.telephone, R.raw.sofa, R.raw.armchair, R.raw.television, R.raw.table, R.raw.lamp, R.raw.carpet, R.raw.chandelier, R.raw.fireplace, R.raw.clock, R.raw.vase};
            image = new int[]{R.drawable.telephone, R.drawable.sofa, R.drawable.armchair, R.drawable.television, R.drawable.table, R.drawable.lamp, R.drawable.carpet, R.drawable.chandelier, R.drawable.fireplace, R.drawable.clock, R.drawable.vase};
        } else if (name.equalsIgnoreCase("Schule")) {
            word = new String[]{"Rucksack", "Buch", "Stift", "Bleistift", "Notizbuch", "Schere", "Radiergummi", "Lineal", "Buntstift", "Klebstoff", "Federtasche"};
            article = new String[]{"der", "das", "der", "der", "das", "die", "der", "das", "der", "der", "die"};
            sound = new int[]{R.raw.backpack, R.raw.book, R.raw.pen, R.raw.pencil, R.raw.notebook, R.raw.scissors, R.raw.eraser, R.raw.ruler, R.raw.color_pencils, R.raw.glue, R.raw.pencil_case};
            image = new int[]{R.drawable.backpack, R.drawable.book, R.drawable.pen, R.drawable.pencil, R.drawable.notebook, R.drawable.scissors, R.drawable.eraser, R.drawable.ruler, R.drawable.color_pencils, R.drawable.glue, R.drawable.pencil_case};
        } else if (name.equalsIgnoreCase("Sport")) {
            word = new String[]{"Baseball", "Golf", "Fußball", "Tennis", "Laufen", "Boxen", "Karate", "Volleyball", "Basketball", "Schwimmen", "Kricket", "Skifahren", "Badminton", "Handball"};
            article = new String[]{"der", "das", "der", "das", "das", "das", "das", "der", "der", "das", "das", "das", "das", "der"};
            sound = new int[]{R.raw.baseball, R.raw.golf, R.raw.soccer, R.raw.tennis, R.raw.running, R.raw.boxing, R.raw.karate, R.raw.volleyball, R.raw.basketball, R.raw.swimming, R.raw.cricket, R.raw.skiing, R.raw.badminton, R.raw.handball};
            image = new int[]{R.drawable.baseball, R.drawable.golf, R.drawable.soccer, R.drawable.tennis, R.drawable.running, R.drawable.boxing, R.drawable.karate, R.drawable.volleyball, R.drawable.basketball, R.drawable.swimming, R.drawable.cricket, R.drawable.skiing, R.drawable.badminton, R.drawable.handball};
        } else if (name.equalsIgnoreCase("Familie")) {
            word = new String[]{"Großvater", "Großmutter", "Großeltern", "Vater", "Mutter", "Eltern", "Sohn", "Tochter", "Schwester", "Bruder"};
            article = new String[]{"der", "die", "die", "der", "die", "die", "der", "die", "die", "der"};
            sound = new int[]{R.raw.grandfather, R.raw.grandmother, R.raw.grandparents, R.raw.father, R.raw.mother, R.raw.parents, R.raw.son, R.raw.daughter, R.raw.sister, R.raw.brother};
            image = new int[]{R.drawable.grandfather, R.drawable.grandmother, R.drawable.grandparents, R.drawable.father, R.drawable.mother, R.drawable.parents, R.drawable.son, R.drawable.daughter, R.drawable.sister, R.drawable.brother};
        } else if (name.equalsIgnoreCase("Körperteile")) {
            word = new String[]{"Kopf", "Gesicht", "Haar", "Auge", "Nase", "Ohr", "Mund", "Lippe", "Zahn", "Nacken", "Schulter", "Brust", "Bauch", "Arm", "Ellenbogen", "Hand", "Finger", "Nagel", "Bein", "Knie", "Fuß", "Knöchel", "Zeh"};
            article = new String[]{"der", "das", "das", "das", "die", "das", "der", "die", "der", "der", "die", "die", "der", "der", "der", "die", "der", "der", "das", "das", "der", "der", "der"};
            sound = new int[]{R.raw.head, R.raw.face, R.raw.hair, R.raw.eye, R.raw.nose, R.raw.ear, R.raw.mouth, R.raw.lip, R.raw.tooth, R.raw.neck, R.raw.shoulder, R.raw.chest, R.raw.stomach, R.raw.arm, R.raw.elbow, R.raw.hand, R.raw.finger, R.raw.nail, R.raw.leg, R.raw.knee, R.raw.foot, R.raw.ankle, R.raw.toe};
            image = new int[]{R.drawable.head, R.drawable.face, R.drawable.hair, R.drawable.eye, R.drawable.nose, R.drawable.ear, R.drawable.mouth, R.drawable.lip, R.drawable.tooth, R.drawable.neck, R.drawable.shoulder, R.drawable.chest, R.drawable.stomach, R.drawable.arm, R.drawable.elbow, R.drawable.hand, R.drawable.finger, R.drawable.nail, R.drawable.leg, R.drawable.knee, R.drawable.foot, R.drawable.ankle, R.drawable.toe};
        } else if (name.equalsIgnoreCase("Berufe")) {
            word = new String[]{"Feuerwehrmann", "Polizist", "Lehrer", "Arzt", "Krankenschwester", "Astronaut", "Ingenieur", "Koch", "Soldat", "Mechaniker", "Pilot", "Wissenschaftler"};
            article = new String[]{"der", "der", "der", "der", "die", "der", "der", "der", "der", "der", "der", "der"};
            sound = new int[]{R.raw.fireman, R.raw.policeman, R.raw.teacher, R.raw.doctor, R.raw.nurse, R.raw.astronaut, R.raw.engineer, R.raw.chef, R.raw.soldier, R.raw.mechanic, R.raw.pilot, R.raw.scientist};
            image = new int[]{R.drawable.fireman, R.drawable.policeman, R.drawable.teacher, R.drawable.doctor, R.drawable.nurse, R.drawable.astronaut, R.drawable.engineer, R.drawable.chef, R.drawable.soldier, R.drawable.mechanic, R.drawable.pilot, R.drawable.scientist};
        } else if (name.equalsIgnoreCase("Tiere 2")) {
            word = new String[]{"Lama", "Büffel", "Hamster", "Meerschweinchen", "Leopard", "Eisbär", "Hirsch", "Wolf", "Fuchs", "Nilpferd", "Nashorn", "Känguru", "Eichhörnchen", "Waschbär", "Igel"};
            article = new String[]{"das", "der", "der", "das", "der", "der", "der", "der", "der", "das", "das", "das", "das", "der", "der"};
            sound = new int[]{R.raw.llama, R.raw.buffalo, R.raw.hamster, R.raw.guinea_pig, R.raw.leopard, R.raw.polar_bear, R.raw.deer, R.raw.wolf, R.raw.fox, R.raw.hippopotamus, R.raw.rhinoceros, R.raw.kangaroo, R.raw.squirrel, R.raw.raccoon, R.raw.hedgehog};
            image = new int[]{R.drawable.llama, R.drawable.buffalo, R.drawable.hamster, R.drawable.guinea_pig, R.drawable.leopard, R.drawable.polar_bear, R.drawable.deer, R.drawable.wolf, R.drawable.fox, R.drawable.hippopotamus, R.drawable.rhinoceros, R.drawable.kangaroo, R.drawable.squirrel, R.drawable.raccoon, R.drawable.hedgehog};
        } else if (name.equalsIgnoreCase("Meerestiere")) {
            word = new String[]{"Delphin", "Hai", "Wal", "Schildkröte", "Garnele", "Hummer", "Krabbe", "Seepferdchen", "Krake", "Aal", "Seestern", "Seelöwe", "Fisch"};
            article = new String[]{"der", "der", "der", "die", "die", "der", "die", "das", "der", "der", "der", "der", "der"};
            sound = new int[]{R.raw.dolphin, R.raw.shark, R.raw.whale, R.raw.turtle, R.raw.shrimp, R.raw.lobster, R.raw.crab, R.raw.seahorse, R.raw.octopus, R.raw.eel, R.raw.starfish, R.raw.sea_lion, R.raw.fish};
            image = new int[]{R.drawable.dolphin, R.drawable.shark, R.drawable.whale, R.drawable.turtle, R.drawable.shrimp, R.drawable.lobster, R.drawable.crab, R.drawable.seahorse, R.drawable.octopus, R.drawable.eel, R.drawable.starfish, R.drawable.sea_lion, R.drawable.fish_animal};
        } else if (name.equalsIgnoreCase("Vögel 2")) {
            word = new String[]{"Küken", "Entlein", "Falke", "Geier", "Krähe", "Pfau", "Pelikan", "Spatz", "Möwe", "Schwan", "Flamingo"};
            article = new String[]{"das", "das", "der", "der", "die", "der", "der", "der", "die", "der", "der"};
            sound = new int[]{R.raw.chick, R.raw.duckling, R.raw.falcon, R.raw.vulture, R.raw.crow, R.raw.peacock, R.raw.pelican, R.raw.sparrow, R.raw.gull, R.raw.swan, R.raw.flamingo};
            image = new int[]{R.drawable.chick, R.drawable.duckling, R.drawable.falcon, R.drawable.vulture, R.drawable.crow, R.drawable.peacock, R.drawable.pelican, R.drawable.sparrow, R.drawable.gull, R.drawable.swan, R.drawable.flamingo};
        } else if (name.equalsIgnoreCase("Insekten")) {
            word = new String[]{"Ameise", "Biene", "Schmetterling", "Kakerlake", "Käfer", "Libelle", "Fliege", "Marienkäfer", "Mücke", "Wespe", "Skorpion", "Schnecke", "Spinne"};
            article = new String[]{"die", "die", "der", "die", "der", "die", "die", "der", "die", "die", "der", "die", "die"};
            sound = new int[]{R.raw.ant, R.raw.bee, R.raw.butterfly, R.raw.cockroach, R.raw.beetle, R.raw.dragonfly, R.raw.fly, R.raw.ladybug, R.raw.mosquito, R.raw.wasp, R.raw.scorpion, R.raw.snail, R.raw.spider};
            image = new int[]{R.drawable.ant, R.drawable.bee, R.drawable.butterfly, R.drawable.cockroach, R.drawable.beetle, R.drawable.dragonfly, R.drawable.fly, R.drawable.ladybug, R.drawable.mosquito, R.drawable.wasp, R.drawable.scorpion, R.drawable.snail, R.drawable.spider};
        } else if (name.equalsIgnoreCase("Musikinstrumente")) {
            word = new String[]{"Trommel", "Xylophon", "Trompete", "Flöte", "Klarinette", "Saxophon", "Gitarre", "Violine", "Cello", "Piano", "Akkordeon"};
            article = new String[]{"die", "das", "die", "die", "die", "das", "die", "die", "das", "das", "das"};
            sound = new int[]{R.raw.drum, R.raw.xylophone, R.raw.trumpet, R.raw.flute, R.raw.clarinet, R.raw.saxophone, R.raw.guitar, R.raw.violin, R.raw.cello, R.raw.piano, R.raw.accordion};
            image = new int[]{R.drawable.drum, R.drawable.xylophone, R.drawable.trumpet, R.drawable.flute, R.drawable.clarinet, R.drawable.saxophone, R.drawable.guitar, R.drawable.violin, R.drawable.cello, R.drawable.piano, R.drawable.accordion};
        } else if (name.equalsIgnoreCase("Transport")) {
            word = new String[]{"Fahrrad", "Motorrad", "Auto", "Bus", "Zug", "Schiff", "Boot", "Flugzeug", "Hubschrauber", "Lastkraftwagen"};
            article = new String[]{"das", "das", "das", "der", "der", "das", "das", "das", "der", "der"};
            sound = new int[]{R.raw.bicycle, R.raw.motorcycle, R.raw.car, R.raw.bus, R.raw.train, R.raw.ship, R.raw.boat, R.raw.plane, R.raw.helicopter, R.raw.truck};
            image = new int[]{R.drawable.bicycle, R.drawable.motorcycle, R.drawable.car, R.drawable.bus, R.drawable.train, R.drawable.ship, R.drawable.boat, R.drawable.plane, R.drawable.helicopter, R.drawable.truck};
        } else if (name.equalsIgnoreCase("Spiele")) {
            word = new String[]{"Murmel", "Schach", "Backgammon", "Bowling", "Billard", "Tischtennis", "Domino", "Karten", "Tischfußball"};
            article = new String[]{"die", "das", "das", "das", "das", "das", "das", "die", "der"};
            sound = new int[]{R.raw.marbles, R.raw.chess, R.raw.backgammon, R.raw.bowling, R.raw.billiards, R.raw.table_tennis, R.raw.dominoes, R.raw.cards, R.raw.foosball, R.raw.snakes_and_ladders};
            image = new int[]{R.drawable.marbles, R.drawable.chess, R.drawable.backgammon, R.drawable.bowling, R.drawable.billiards, R.drawable.table_tennis, R.drawable.dominoes, R.drawable.cards, R.drawable.foosball, R.drawable.snakes_and_ladders};
        }
        int i = 0;
        while (true) {
            String[] strArr = word;
            if (i < strArr.length) {
                wordsList.add(strArr[i]);
                articleList.add(article[i]);
                soundsList.add(Integer.valueOf(sound[i]));
                imagesList.add(Integer.valueOf(image[i]));
                i++;
            } else {
                long nanoTime = System.nanoTime();
                Collections.shuffle(wordsList, new Random(nanoTime));
                Collections.shuffle(articleList, new Random(nanoTime));
                Collections.shuffle(soundsList, new Random(nanoTime));
                Collections.shuffle(imagesList, new Random(nanoTime));
                return;
            }
        }
    }
    
    public void addItems() {
        imgQue.setImageResource(imagesList.get(position).intValue());
        txtWord.setText(wordsList.get(position).trim());
        txtArticle.setText(".....");
    }

    public void answer(View view) {
        imgNext.setEnabled(true);
        switch (view.getId()) {
            case R.id.txtDas:
                checkAnswer(txtDas);
                return;
            case R.id.txtDer:
                checkAnswer(txtDer);
                return;
            case R.id.txtDie :
                checkAnswer(txtDie);
                return;
            default:
                return;
        }
    }

    private void checkAnswer(TextView textView) {
        if (textView.getText().toString().equals(articleList.get(position))) {
            YoYo.with(Techniques.Flash).duration(700L).repeat(0).playOn(textView);
            speak(soundsList.get(position).intValue());
            txtArticle.setText(textView.getText().toString());
            textView.setBackgroundResource(R.drawable.find_the_word_btn_correct_ans);
            txtDer.setEnabled(false);
            txtDie.setEnabled(false);
            txtDas.setEnabled(false);
            imgNext.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.ZoomInLeft).duration(700L).repeat(0).playOn(imgNext);
        } else {
            YoYo.with(Techniques.Shake).duration(700L).repeat(0).playOn(textView);
            speak(R.raw.wrong_answer);
            textView.setBackgroundResource(R.drawable.find_the_word_btn_wrong_ans);
            checkCorrectAns = false;
        }
        if (position == wordsList.size() - 1) {
            imgNext.setImageResource(R.drawable.ic_result);
        }
        if (checkCorrectAns) {
            correct_answer++;
        }
    }

    public void next(View view) {
        imgNext.setEnabled(false);
        YoYo.with(Techniques.ZoomOutRight).duration(500L).repeat(0).playOn(imgNext);
        checkCorrectAns = true;
        if (position == wordsList.size() - 1) {
        } else {
            position++;
            addItems();
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(imgQue);
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(txtWord);
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(txtArticle);
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(txtDer);
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(txtDie);
            YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(txtDas);
            YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.imgQue);
        }
        txtDer.setEnabled(true);
        txtDie.setEnabled(true);
        txtDas.setEnabled(true);
        txtDer.setBackgroundResource(R.drawable.find_the_word_btn_base);
        txtDie.setBackgroundResource(R.drawable.find_the_word_btn_base);
        txtDas.setBackgroundResource(R.drawable.find_the_word_btn_base);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBacks:
                onBackPressed();
                break;
        }
    }
}
