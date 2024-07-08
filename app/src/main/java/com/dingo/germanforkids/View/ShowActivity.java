package com.dingo.germanforkids.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityShowBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ShowActivity extends AppCompatActivity implements View.OnClickListener{
    private int[] image;
    private ArrayList<Integer> imagesList;
    private ImageView imgBack;
    private ImageView imgNext;
    private ImageView imgWord;
    
    private MediaPlayer mediaPlayer;
    private String name;
    private boolean noAds;
    private int position = 0;
    private int[] sound;
    private ArrayList<Integer> soundsList;
    private TextView txtWord;
    private String[] word;
    private ArrayList<String> wordsList;

    ActivityShowBinding binding;
    @Override
    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readSharedPrefForAdsAndMoreSub();
        initComponents();
        lessonData();
        binding.imgBacks.setOnClickListener(this);
        YoYo.with(Techniques.Swing).duration(1200).repeat(2).playOn(binding.imgWord);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBacks:
                onBackPressed();
                break;
        }
    }

    private void readSharedPrefForAdsAndMoreSub() {
        noAds = getSharedPreferences("store", 0).getBoolean("removeAds", false);
    }

    private void initComponents() {
        imgWord = (ImageView) findViewById(R.id.imgWord);
        txtWord = (TextView) findViewById(R.id.txtWord);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        wordsList = new ArrayList<>();
        soundsList = new ArrayList<>();
        imagesList = new ArrayList<>();
        imgBack.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }
        binding.txtTittle.setText(name);
    }

    private void lessonData() {
        if (name.equalsIgnoreCase("Alphabet")) {
            word = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            sound = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k, R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w, R.raw.x, R.raw.y, R.raw.z};
            image = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z};
        } else if (name.equalsIgnoreCase("Zahlen")) {
            word = new String[]{"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn", "elf", "zwölf", "dreizehn", "vierzehn", "fünfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn", "zwanzig", "einundzwanzig", "zweiundzwanzig", "dreißig", "vierzig", "fünfzig", "sechzig", "siebzig", "achtzig", "neunzig", "einhundert"};
            sound = new int[]{R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine, R.raw.ten, R.raw.eleven, R.raw.twelve, R.raw.thirteen, R.raw.fourteen, R.raw.fifteen, R.raw.sixteen, R.raw.seventeen, R.raw.eighteen, R.raw.nineteen, R.raw.twenty, R.raw.twenty_one, R.raw.twenty_two, R.raw.thirty, R.raw.forty, R.raw.fifty, R.raw.sixty, R.raw.seventy, R.raw.eighty, R.raw.ninety, R.raw.one_hundred};
            image = new int[]{R.drawable.zero, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.twelve, R.drawable.thirteen, R.drawable.fourteen, R.drawable.fifteen, R.drawable.sixteen, R.drawable.seventeen, R.drawable.eighteen, R.drawable.nineteen, R.drawable.twenty, R.drawable.twenty_one, R.drawable.twenty_two, R.drawable.thirty, R.drawable.forty, R.drawable.fifty, R.drawable.sixty, R.drawable.seventy, R.drawable.eighty, R.drawable.ninety, R.drawable.one_hundred};
        } else if (name.equalsIgnoreCase("Farben")) {
            word = new String[]{"schwarz", "weiß", "blau", "braun", "grau", "grün", "orange", "rosa", "lila", "rot", "gelb"};
            sound = new int[]{R.raw.black, R.raw.white, R.raw.blue, R.raw.brown, R.raw.gray, R.raw.green, R.raw.orange, R.raw.pink, R.raw.purple, R.raw.red, R.raw.yellow};
            image = new int[]{R.drawable.black, R.drawable.white, R.drawable.blue, R.drawable.brown, R.drawable.gray, R.drawable.green, R.drawable.orange, R.drawable.pink, R.drawable.purple, R.drawable.red, R.drawable.yellow};
        } else if (name.equalsIgnoreCase("Formen")) {
            word = new String[]{"der Kreis", "das Quadrat", "das Dreieck", "das Rechteck", "das Oval", "das Herz", "der Diamant", "der Stern", "das Sechseck"};
            sound = new int[]{R.raw.circle, R.raw.square, R.raw.triangle, R.raw.rectangle, R.raw.oval, R.raw.heart, R.raw.diamond, R.raw.star, R.raw.hexagon};
            image = new int[]{R.drawable.circle, R.drawable.square, R.drawable.triangle, R.drawable.rectangle, R.drawable.oval, R.drawable.heart, R.drawable.diamond, R.drawable.star, R.drawable.hexagon};
        } else if (name.equalsIgnoreCase("Wochentage")) {
            word = new String[]{"der Montag", "der Dienstag", "der Mittwoch", "der Donnerstag", "der Freitag", "der Samstag", "der Sonntag"};
            sound = new int[]{R.raw.monday, R.raw.tuesday, R.raw.wednesday, R.raw.thursday, R.raw.friday, R.raw.saturday, R.raw.sunday};
            image = new int[]{R.drawable.monday, R.drawable.tuesday, R.drawable.wednesday, R.drawable.thursday, R.drawable.friday, R.drawable.saturday, R.drawable.sunday};
        } else if (name.equalsIgnoreCase("Monate")) {
            word = new String[]{"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
            sound = new int[]{R.raw.january, R.raw.february, R.raw.march, R.raw.april, R.raw.may, R.raw.june, R.raw.july, R.raw.august, R.raw.september, R.raw.october, R.raw.november, R.raw.december};
            image = new int[]{R.drawable.january, R.drawable.february, R.drawable.march, R.drawable.april, R.drawable.may, R.drawable.june, R.drawable.july, R.drawable.august, R.drawable.september, R.drawable.october, R.drawable.november, R.drawable.december};
        } else if (name.equalsIgnoreCase("Obst")) {
            word = new String[]{"die Wassermelone", "die Banane", "die Traube", "der Pfirsich", "die Grapefruit", "die Zitrone", "die Mandarine", "die Erdbeere", "die Feige", "die Kiwi", "der Apfel", "die Pflaume", "die Birne", "die Orange", "die Melone", "die Ananas", "der Granatapfel", "die Aprikose", "die Kirsche"};
            sound = new int[]{R.raw.watermelon, R.raw.banana, R.raw.grapes, R.raw.peach, R.raw.grapefruit, R.raw.lemon, R.raw.tangerine, R.raw.strawberry, R.raw.fig, R.raw.kiwi, R.raw.apple, R.raw.plum, R.raw.pear, R.raw.orange_fruit, R.raw.melon, R.raw.pineapple, R.raw.pomegranate, R.raw.apricot, R.raw.cherry};
            image = new int[]{R.drawable.watermelon, R.drawable.banana, R.drawable.grapes, R.drawable.peach, R.drawable.grapefruit, R.drawable.lemon, R.drawable.tangerine, R.drawable.strawberry, R.drawable.fig, R.drawable.kiwi, R.drawable.apple, R.drawable.plum, R.drawable.pear, R.drawable.orange_fruit, R.drawable.melon, R.drawable.pineapple, R.drawable.pomegranate, R.drawable.apricot, R.drawable.cherry};
        } else if (name.equalsIgnoreCase("Gemüse")) {
            word = new String[]{"die Karotte", "der Knoblauch", "die Kartoffel", "die Tomate", "die Gurke", "der Mais", "die Paprika", "der Kohl", "der Blumenkohl", "die Zwiebel", "der Kopfsalat", "die Aubergine", "der Lauch", "die Okra"};
            sound = new int[]{R.raw.carrot, R.raw.garlic, R.raw.potato, R.raw.tomato, R.raw.cucumber, R.raw.corn, R.raw.bell_pepper, R.raw.cabbage, R.raw.cauliflower, R.raw.onion, R.raw.lettuce, R.raw.eggplant, R.raw.leek, R.raw.okra};
            image = new int[]{R.drawable.carrot, R.drawable.garlic, R.drawable.potato, R.drawable.tomato, R.drawable.cucumber, R.drawable.corn, R.drawable.bell_pepper, R.drawable.cabbage, R.drawable.cauliflower, R.drawable.onion, R.drawable.lettuce, R.drawable.eggplant, R.drawable.leek, R.drawable.okra};
        } else if (name.equalsIgnoreCase("Tiere")) {
            word = new String[]{"das Pferd", "der Esel", "das Kamel", "die Kuh", "das Schwein", "das Schaf", "die Ziege", "die Katze", "der Hund", "das Kaninchen", "die Maus", "der Löwe", "der Tiger", "die Giraffe", "der Elefant", "der Bär", "das Zebra", "der Affe", "der Panda", "das Krokodil", "die Schlange", "die Schildkröte"};
            sound = new int[]{R.raw.horse, R.raw.donkey, R.raw.camel, R.raw.cow, R.raw.pig, R.raw.sheep, R.raw.goat, R.raw.cat, R.raw.dog, R.raw.rabbit, R.raw.mouse, R.raw.lion, R.raw.tiger, R.raw.giraffe, R.raw.elephant, R.raw.bear, R.raw.zebra, R.raw.monkey, R.raw.panda, R.raw.crocodile, R.raw.snake, R.raw.tortoise};
            image = new int[]{R.drawable.horse, R.drawable.donkey, R.drawable.camel, R.drawable.cow, R.drawable.pig, R.drawable.sheep, R.drawable.goat, R.drawable.cat, R.drawable.dog, R.drawable.rabbit, R.drawable.mouse, R.drawable.lion, R.drawable.tiger, R.drawable.giraffe, R.drawable.elephant, R.drawable.bear, R.drawable.zebra, R.drawable.monkey, R.drawable.panda, R.drawable.crocodile, R.drawable.snake, R.drawable.tortoise};
        } else if (name.equalsIgnoreCase("Vögel")) {
            word = new String[]{"das Huhn", "der Hahn", "die Ente", "die Gans", "die Pute", "die Taube", "der Adler", "die Eule", "der Papagei", "der Strauß", "der Pinguin"};
            sound = new int[]{R.raw.chicken, R.raw.rooster, R.raw.duck, R.raw.goose, R.raw.turkey, R.raw.pigeon, R.raw.eagle, R.raw.owl, R.raw.parrot, R.raw.ostrich, R.raw.penguin};
            image = new int[]{R.drawable.chicken, R.drawable.rooster, R.drawable.duck, R.drawable.goose, R.drawable.turkey, R.drawable.pigeon, R.drawable.eagle, R.drawable.owl, R.drawable.parrot, R.drawable.ostrich, R.drawable.penguin};
        } else if (name.equalsIgnoreCase("Essen")) {
            word = new String[]{"die Pizza", "der Hamburger", "das Brot", "der Hot Dog", "das Ei", "der Salat", "der Käse", "der Fisch", "der Reis", "die Suppe", "das Hähnchen", "das Eis", "der Pfannkuchen", "der Kuchen", "die Schokolade", "der Saft", "die Milch", "das Wasser"};
            sound = new int[]{R.raw.pizza, R.raw.hamburger, R.raw.bread, R.raw.hot_dog, R.raw.egg, R.raw.salad, R.raw.cheese, R.raw.fish, R.raw.rice, R.raw.soup, R.raw.chicken_food, R.raw.ice_cream, R.raw.pancake, R.raw.cake, R.raw.chocolate, R.raw.juice, R.raw.milk, R.raw.water};
            image = new int[]{R.drawable.pizza, R.drawable.hamburger, R.drawable.bread, R.drawable.hot_dog, R.drawable.egg, R.drawable.salad, R.drawable.cheese, R.drawable.fish, R.drawable.rice, R.drawable.soup, R.drawable.chicken_food, R.drawable.ice_cream, R.drawable.pancake, R.drawable.cake, R.drawable.chocolate, R.drawable.juice, R.drawable.milk, R.drawable.water};
        } else if (name.equalsIgnoreCase("Kleidung")) {
            word = new String[]{"die Jeans", "die Bluse", "der Rock", "der Pullover", "die Hose", "das Kleid", "die Jacke", "die Weste", "der Mantel", "die Kappe", "der Hut", "die Handschuhe", "der Schal", "der Stiefel", "die Turnschuhe", "die Schuhe", "die Tasche", "das T-Shirt", "das Hemd", "der Gürtel", "die Krawatte", "die Socken"};
            sound = new int[]{R.raw.jeans, R.raw.blouse, R.raw.skirt, R.raw.sweater, R.raw.pants, R.raw.dress, R.raw.jacket, R.raw.vest, R.raw.coat, R.raw.cap, R.raw.hat, R.raw.gloves, R.raw.scarf, R.raw.boots, R.raw.sneakers, R.raw.shoes, R.raw.handbag, R.raw.tshirt, R.raw.shirt, R.raw.belt, R.raw.tie, R.raw.socks};
            image = new int[]{R.drawable.jeans, R.drawable.blouse, R.drawable.skirt, R.drawable.sweater, R.drawable.pants, R.drawable.dress, R.drawable.jacket, R.drawable.vest, R.drawable.coat, R.drawable.cap, R.drawable.hat, R.drawable.gloves, R.drawable.scarf, R.drawable.boots, R.drawable.sneakers, R.drawable.shoes, R.drawable.handbag, R.drawable.tshirt, R.drawable.shirt, R.drawable.belt, R.drawable.tie, R.drawable.socks};
        } else if (name.equalsIgnoreCase("Küche")) {
            word = new String[]{"der Kühlschrank", "der Herd", "der Wasserkocher", "das Messer", "der Löffel", "die Gabel", "die Reibe", "die Bratpfanne", "der Topf", "das Glas", "die Tasse", "der Teller", "die Schüssel", "der Schöpfkelle", "die Flasche"};
            sound = new int[]{R.raw.refrigerator, R.raw.oven, R.raw.kettle, R.raw.knife, R.raw.spoon, R.raw.fork, R.raw.grater, R.raw.frying_pan, R.raw.pot, R.raw.glass, R.raw.cup, R.raw.plate, R.raw.bowl, R.raw.ladle, R.raw.bottle};
            image = new int[]{R.drawable.refrigerator, R.drawable.oven, R.drawable.kettle, R.drawable.knife, R.drawable.spoon, R.drawable.fork, R.drawable.grater, R.drawable.frying_pan, R.drawable.pot, R.drawable.glass, R.drawable.cup, R.drawable.plate, R.drawable.bowl, R.drawable.ladle, R.drawable.bottle};
        } else if (name.equalsIgnoreCase("Badezimmer")) {
            word = new String[]{"die Badewanne", "die Dusche", "das Waschbecken", "der Spiegel", "das Handtuch", "die Toilette", "die Seife", "das Toilettenpapier", "die Zahnbürste", "die Zahnpasta", "das Shampoo", "der Föhn"};
            sound = new int[]{R.raw.bathtub, R.raw.shower, R.raw.sink, R.raw.mirror, R.raw.towel, R.raw.toilet, R.raw.soap, R.raw.toilet_paper, R.raw.toothbrush, R.raw.toothpaste, R.raw.shampoo, R.raw.hairdryer};
            image = new int[]{R.drawable.bathtub, R.drawable.shower, R.drawable.sink, R.drawable.mirror, R.drawable.towel, R.drawable.toilet, R.drawable.soap, R.drawable.toilet_paper, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.shampoo, R.drawable.hairdryer};
        } else if (name.equalsIgnoreCase("Wohnzimmer")) {
            word = new String[]{"das Telefon", "das Sofa", "der Sessel", "der Fernseher", "der Tisch", "die Lampe", "der Teppich", "der Kronleuchter", "der Kamin", "die Uhr", "die Vase"};
            sound = new int[]{R.raw.telephone, R.raw.sofa, R.raw.armchair, R.raw.television, R.raw.table, R.raw.lamp, R.raw.carpet, R.raw.chandelier, R.raw.fireplace, R.raw.clock, R.raw.vase};
            image = new int[]{R.drawable.telephone, R.drawable.sofa, R.drawable.armchair, R.drawable.television, R.drawable.table, R.drawable.lamp, R.drawable.carpet, R.drawable.chandelier, R.drawable.fireplace, R.drawable.clock, R.drawable.vase};
        } else if (name.equalsIgnoreCase("Schule")) {
            word = new String[]{"der Rucksack", "das Buch", "der Stift", "der Bleistift", "das Notizbuch", "die Schere", "der Radiergummi", "das Lineal", "der Buntstift", "der Klebstoff", "die Federtasche"};
            sound = new int[]{R.raw.backpack, R.raw.book, R.raw.pen, R.raw.pencil, R.raw.notebook, R.raw.scissors, R.raw.eraser, R.raw.ruler, R.raw.color_pencils, R.raw.glue, R.raw.pencil_case};
            image = new int[]{R.drawable.backpack, R.drawable.book, R.drawable.pen, R.drawable.pencil, R.drawable.notebook, R.drawable.scissors, R.drawable.eraser, R.drawable.ruler, R.drawable.color_pencils, R.drawable.glue, R.drawable.pencil_case};
        } else if (name.equalsIgnoreCase("Sport")) {
            word = new String[]{"der Baseball", "das Golf", "der Fußball", "das Tennis", "das Laufen", "das Boxen", "das Karate", "der Volleyball", "der Basketball", "das Schwimmen", "das Kricket", "das Skifahren", "das Badminton", "der Handball"};
            sound = new int[]{R.raw.baseball, R.raw.golf, R.raw.soccer, R.raw.tennis, R.raw.running, R.raw.boxing, R.raw.karate, R.raw.volleyball, R.raw.basketball, R.raw.swimming, R.raw.cricket, R.raw.skiing, R.raw.badminton, R.raw.handball};
            image = new int[]{R.drawable.baseball, R.drawable.golf, R.drawable.soccer, R.drawable.tennis, R.drawable.running, R.drawable.boxing, R.drawable.karate, R.drawable.volleyball, R.drawable.basketball, R.drawable.swimming, R.drawable.cricket, R.drawable.skiing, R.drawable.badminton, R.drawable.handball};
        } else if (name.equalsIgnoreCase("Familie")) {
            word = new String[]{"der Großvater", "die Großmutter", "die Großeltern", "der Vater", "die Mutter", "die Eltern", "der Sohn", "die Tochter", "die Schwester", "der Bruder"};
            sound = new int[]{R.raw.grandfather, R.raw.grandmother, R.raw.grandparents, R.raw.father, R.raw.mother, R.raw.parents, R.raw.son, R.raw.daughter, R.raw.sister, R.raw.brother};
            image = new int[]{R.drawable.grandfather, R.drawable.grandmother, R.drawable.grandparents, R.drawable.father, R.drawable.mother, R.drawable.parents, R.drawable.son, R.drawable.daughter, R.drawable.sister, R.drawable.brother};
        } else if (name.equalsIgnoreCase("Körperteile")) {
            word = new String[]{"der Kopf", "das Gesicht", "das Haar", "das Auge", "die Nase", "das Ohr", "der Mund", "die Lippe", "der Zahn", "der Nacken", "die Schulter", "die Brust", "der Bauch", "der Arm", "der Ellenbogen", "die Hand", "der Finger", "der Nagel", "das Bein", "das Knie", "der Fuß", "der Knöchel", "der Zeh"};
            sound = new int[]{R.raw.head, R.raw.face, R.raw.hair, R.raw.eye, R.raw.nose, R.raw.ear, R.raw.mouth, R.raw.lip, R.raw.tooth, R.raw.neck, R.raw.shoulder, R.raw.chest, R.raw.stomach, R.raw.arm, R.raw.elbow, R.raw.hand, R.raw.finger, R.raw.nail, R.raw.leg, R.raw.knee, R.raw.foot, R.raw.ankle, R.raw.toe};
            image = new int[]{R.drawable.head, R.drawable.face, R.drawable.hair, R.drawable.eye, R.drawable.nose, R.drawable.ear, R.drawable.mouth, R.drawable.lip, R.drawable.tooth, R.drawable.neck, R.drawable.shoulder, R.drawable.chest, R.drawable.stomach, R.drawable.arm, R.drawable.elbow, R.drawable.hand, R.drawable.finger, R.drawable.nail, R.drawable.leg, R.drawable.knee, R.drawable.foot, R.drawable.ankle, R.drawable.toe};
        } else if (name.equalsIgnoreCase("Berufe")) {
            word = new String[]{"der Feuerwehrmann", "der Polizist", "der Lehrer", "der Arzt", "die Krankenschwester", "der Astronaut", "der Ingenieur", "der Koch", "der Soldat", "der Mechaniker", "der Pilot", "der Wissenschaftler"};
            sound = new int[]{R.raw.fireman, R.raw.policeman, R.raw.teacher, R.raw.doctor, R.raw.nurse, R.raw.astronaut, R.raw.engineer, R.raw.chef, R.raw.soldier, R.raw.mechanic, R.raw.pilot, R.raw.scientist};
            image = new int[]{R.drawable.fireman, R.drawable.policeman, R.drawable.teacher, R.drawable.doctor, R.drawable.nurse, R.drawable.astronaut, R.drawable.engineer, R.drawable.chef, R.drawable.soldier, R.drawable.mechanic, R.drawable.pilot, R.drawable.scientist};
        } else if (name.equalsIgnoreCase("Tiere 2")) {
            word = new String[]{"das Lama", "der Büffel", "der Hamster", "das Meerschweinchen", "der Leopard", "der Eisbär", "der Hirsch", "der Wolf", "der Fuchs", "das Nilpferd", "das Nashorn", "das Känguru", "das Eichhörnchen", "der Waschbär", "der Igel"};
            sound = new int[]{R.raw.llama, R.raw.buffalo, R.raw.hamster, R.raw.guinea_pig, R.raw.leopard, R.raw.polar_bear, R.raw.deer, R.raw.wolf, R.raw.fox, R.raw.hippopotamus, R.raw.rhinoceros, R.raw.kangaroo, R.raw.squirrel, R.raw.raccoon, R.raw.hedgehog};
            image = new int[]{R.drawable.llama, R.drawable.buffalo, R.drawable.hamster, R.drawable.guinea_pig, R.drawable.leopard, R.drawable.polar_bear, R.drawable.deer, R.drawable.wolf, R.drawable.fox, R.drawable.hippopotamus, R.drawable.rhinoceros, R.drawable.kangaroo, R.drawable.squirrel, R.drawable.raccoon, R.drawable.hedgehog};
        } else if (name.equalsIgnoreCase("Meerestiere")) {
            word = new String[]{"der Delphin", "der Hai", "der Wal", "die Schildkröte", "die Garnele", "der Hummer", "die Krabbe", "das Seepferdchen", "der Krake", "der Aal", "der Seestern", "der Seelöwe", "der Fisch"};
            sound = new int[]{R.raw.dolphin, R.raw.shark, R.raw.whale, R.raw.turtle, R.raw.shrimp, R.raw.lobster, R.raw.crab, R.raw.seahorse, R.raw.octopus, R.raw.eel, R.raw.starfish, R.raw.sea_lion, R.raw.fish};
            image = new int[]{R.drawable.dolphin, R.drawable.shark, R.drawable.whale, R.drawable.turtle, R.drawable.shrimp, R.drawable.lobster, R.drawable.crab, R.drawable.seahorse, R.drawable.octopus, R.drawable.eel, R.drawable.starfish, R.drawable.sea_lion, R.drawable.fish_animal};
        } else if (name.equalsIgnoreCase("Vögel 2")) {
            word = new String[]{"das Küken", "das Entlein", "der Falke", "der Geier", "die Krähe", "der Pfau", "der Pelikan", "der Spatz", "die Möwe", "der Schwan", "der Flamingo"};
            sound = new int[]{R.raw.chick, R.raw.duckling, R.raw.falcon, R.raw.vulture, R.raw.crow, R.raw.peacock, R.raw.pelican, R.raw.sparrow, R.raw.gull, R.raw.swan, R.raw.flamingo};
            image = new int[]{R.drawable.chick, R.drawable.duckling, R.drawable.falcon, R.drawable.vulture, R.drawable.crow, R.drawable.peacock, R.drawable.pelican, R.drawable.sparrow, R.drawable.gull, R.drawable.swan, R.drawable.flamingo};
        } else if (name.equalsIgnoreCase("Insekten")) {
            word = new String[]{"die Ameise", "die Biene", "der Schmetterling", "die Kakerlake", "der Käfer", "die Libelle", "die Fliege", "der Marienkäfer", "die Mücke", "die Wespe", "der Skorpion", "die Schnecke", "die Spinne"};
            sound = new int[]{R.raw.ant, R.raw.bee, R.raw.butterfly, R.raw.cockroach, R.raw.beetle, R.raw.dragonfly, R.raw.fly, R.raw.ladybug, R.raw.mosquito, R.raw.wasp, R.raw.scorpion, R.raw.snail, R.raw.spider};
            image = new int[]{R.drawable.ant, R.drawable.bee, R.drawable.butterfly, R.drawable.cockroach, R.drawable.beetle, R.drawable.dragonfly, R.drawable.fly, R.drawable.ladybug, R.drawable.mosquito, R.drawable.wasp, R.drawable.scorpion, R.drawable.snail, R.drawable.spider};
        } else if (name.equalsIgnoreCase("Musikinstrumente")) {
            word = new String[]{"die Trommel", "das Xylophon", "die Trompete", "die Flöte", "die Klarinette", "das Saxophon", "die Gitarre", "die Violine", "das Cello", "das Piano", "das Akkordeon"};
            sound = new int[]{R.raw.drum, R.raw.xylophone, R.raw.trumpet, R.raw.flute, R.raw.clarinet, R.raw.saxophone, R.raw.guitar, R.raw.violin, R.raw.cello, R.raw.piano, R.raw.accordion};
            image = new int[]{R.drawable.drum, R.drawable.xylophone, R.drawable.trumpet, R.drawable.flute, R.drawable.clarinet, R.drawable.saxophone, R.drawable.guitar, R.drawable.violin, R.drawable.cello, R.drawable.piano, R.drawable.accordion};
        } else if (name.equalsIgnoreCase("Transport")) {
            word = new String[]{"das Fahrrad", "das Motorrad", "das Auto", "der Bus", "der Zug", "das Schiff", "das Boot", "das Flugzeug", "der Hubschrauber", "der Lastkraftwagen"};
            sound = new int[]{R.raw.bicycle, R.raw.motorcycle, R.raw.car, R.raw.bus, R.raw.train, R.raw.ship, R.raw.boat, R.raw.plane, R.raw.helicopter, R.raw.truck};
            image = new int[]{R.drawable.bicycle, R.drawable.motorcycle, R.drawable.car, R.drawable.bus, R.drawable.train, R.drawable.ship, R.drawable.boat, R.drawable.plane, R.drawable.helicopter, R.drawable.truck};
        } else if (name.equalsIgnoreCase("Spiele")) {
            word = new String[]{"die Murmel", "das Schach", "das Backgammon", "das Bowling", "das Billard", "das Tischtennis", "das Domino", "die Karten", "der Tischfußball", "Schlangen und Leiter"};
            sound = new int[]{R.raw.marbles, R.raw.chess, R.raw.backgammon, R.raw.bowling, R.raw.billiards, R.raw.table_tennis, R.raw.dominoes, R.raw.cards, R.raw.foosball, R.raw.snakes_and_ladders};
            image = new int[]{R.drawable.marbles, R.drawable.chess, R.drawable.backgammon, R.drawable.bowling, R.drawable.billiards, R.drawable.table_tennis, R.drawable.dominoes, R.drawable.cards, R.drawable.foosball, R.drawable.snakes_and_ladders};
        } else if (name.equalsIgnoreCase("Präpositionen")) {
            word = new String[]{"auf", "unter", "über", "vor", "hinter", "neben", "in", "zwischen", "fern von", "in der Nähe von"};
            sound = new int[]{R.raw.on, R.raw.under, R.raw.above, R.raw.in_front_of, R.raw.behind, R.raw.next_to, R.raw.in, R.raw.between, R.raw.far_from, R.raw.near};
            image = new int[]{R.drawable.on, R.drawable.under, R.drawable.above, R.drawable.in_front_of, R.drawable.behind, R.drawable.next_to, R.drawable.in, R.drawable.between, R.drawable.far, R.drawable.near};
        } else if (name.equalsIgnoreCase("Uhrzeit")) {
            word = new String[]{"zwei Uhr", "zwei Uhr fünf", "zwei Uhr zehn", "zwei Uhr fünfzehn", "zwei Uhr zwanzig", "zwei Uhr fünfundzwanzig", "zwei Uhr dreißig", "zwei Uhr fünfunddreißig", "zwei Uhr vierzig", "zwei Uhr fünfundvierzig", "zwei Uhr fünfzig", "zwei Uhr fünfundfünfzig", "drei Uhr"};
            sound = new int[]{R.raw.two_oclock, R.raw.five_past_two, R.raw.ten_past_two, R.raw.quarter_past_two, R.raw.twenty_past_two, R.raw.twenty_five_past_two, R.raw.half_past_two, R.raw.twenty_five_to_three, R.raw.twenty_to_three, R.raw.quarter_to_three, R.raw.ten_to_three, R.raw.five_to_three, R.raw.three_oclock};
            image = new int[]{R.drawable.two_oclock, R.drawable.five_past_two, R.drawable.ten_past_two, R.drawable.quarter_past_two, R.drawable.twenty_past_two, R.drawable.twenty_five_past_two, R.drawable.half_past_two, R.drawable.twenty_five_to_three, R.drawable.twenty_to_three, R.drawable.quarter_to_three, R.drawable.ten_to_three, R.drawable.five_to_three, R.drawable.three_oclock};
            txtWord.setTextSize(22.0f);
        } else if (name.equalsIgnoreCase("Verben")) {
            word = new String[]{"laufen", "helfen", "schwimmen", "kochen", "schlafen", "waschen", "essen", "trinken", "lesen", "schreiben", "zeichnen", "hören", "spielen", "weinen", "lachen", "springen", "gehen", "klettern", "geben", "fahren"};
            sound = new int[]{R.raw.run, R.raw.help, R.raw.swim, R.raw.cook, R.raw.sleep, R.raw.wash, R.raw.eat, R.raw.drink, R.raw.read, R.raw.write, R.raw.draw, R.raw.listen, R.raw.play, R.raw.cry, R.raw.laugh, R.raw.jump, R.raw.walk, R.raw.climb, R.raw.give, R.raw.ride};
            image = new int[]{R.drawable.run, R.drawable.help, R.drawable.swim, R.drawable.cook, R.drawable.sleep, R.drawable.wash, R.drawable.eat, R.drawable.drink, R.drawable.read, R.drawable.write_verb, R.drawable.draw, R.drawable.listen, R.drawable.play, R.drawable.cry, R.drawable.laugh, R.drawable.jump, R.drawable.walk, R.drawable.climb, R.drawable.give, R.drawable.ride};
        }
        int i = 0;
        while (true) {
            String[] strArr = word;
            if (i >= strArr.length) {
                break;
            }
            wordsList.add(strArr[i]);
            soundsList.add(Integer.valueOf(sound[i]));
            imagesList.add(Integer.valueOf(image[i]));
            i++;
        }
        if (!name.equals("Alphabet") && !name.equals("Zahlen") && !name.equals("Wochentage") && !name.equals("Monate") && !name.equals("Familie") && !name.equals("Körperteile") && !name.equals("Präpositionen") && !name.equals("Uhrzeit")) {
            long nanoTime = System.nanoTime();
            Collections.shuffle(wordsList, new Random(nanoTime));
            Collections.shuffle(soundsList, new Random(nanoTime));
            Collections.shuffle(imagesList, new Random(nanoTime));
        }
        imgWord.setImageResource(imagesList.get(position).intValue());
        txtWord.setText(wordsList.get(position));
        MediaPlayer create = MediaPlayer.create(getApplicationContext(), soundsList.get(position).intValue());
        mediaPlayer = create;
        create.start();
    }

    public void imgNext(View view) {
        if (position == -1) {
            if (getSharedPreferences("rateApp", 0).getInt("rateApp", 2) == 2) {

            }
        }
        position++;
        show();
        if (imgBack.getVisibility() == View.INVISIBLE) {
            imgBack.setVisibility(View.VISIBLE);
        }
        speak(soundsList.get(position).intValue());
        if (position == word.length - 1) {
            imgNext.setImageResource(R.drawable.repeat);
            position = -1;
        } else {
            imgNext.setImageResource(R.drawable.right_arrow);
            YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.imgWord);

        }
        if (position == 0) {
            imgBack.setVisibility(View.INVISIBLE);
        }
    }

    public void imgBack(View view) {
        if (position == -1) {
            position = word.length - 1;
            imgNext.setImageResource(R.drawable.right_arrow);
        }
        position--;
        show();
        if (position == 0) {
            imgBack.setVisibility(View.INVISIBLE);
        }
        if (imgNext.getVisibility() == View.INVISIBLE) {
            imgNext.setVisibility(View.VISIBLE);
        }
        speak(soundsList.get(position).intValue());
    }

    public void imgSpeak(View view) {
        int i = position;
        if (i == -1) {
            speak(soundsList.get(word.length - 1).intValue());
        } else {
            speak(soundsList.get(i).intValue());
        }
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

    private void show() {
        imgWord.setImageResource(imagesList.get(position).intValue());
        txtWord.setText(wordsList.get(position));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
