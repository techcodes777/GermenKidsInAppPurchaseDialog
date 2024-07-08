package com.dingo.germanforkids.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityWritingBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Writing extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Character> alphabets;
    private Button btn1;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;
    private Button btn17;
    private Button btn18;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private int charPosition;
    private ArrayList<Character> clearChars;
    private EditText editText;
    private int[] image;
    private ArrayList<Integer> imagesList;
    private ImageView imgNext;
    private ImageView imgWord;


    private ArrayList<Character> mainChars;
    private MediaPlayer mediaPlayer;
    private String name;
    private boolean noAds;
    private int[] sound;
    private ArrayList<Integer> soundsList;
    private String[] word;
    private int wordPosition;
    private ArrayList<String> wordsList;


    ActivityWritingBinding binding;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityWritingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readSharedPrefForAdsAndMoreSub();
        initComponents();
        lessonData();
        addTheWordToButtons(wordsList.get(wordPosition));

        binding.imgBacks.setOnClickListener(this);
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.imgWord);


    }

    private void readSharedPrefForAdsAndMoreSub() {
        noAds = getSharedPreferences("store", 0).getBoolean("removeAds", false);
    }

    private void initComponents() {
        wordsList = new ArrayList<>();
        soundsList = new ArrayList<>();
        imagesList = new ArrayList<>();
        clearChars = new ArrayList<>();
        mainChars = new ArrayList<>();
        alphabets = new ArrayList<>();
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12 = (Button) findViewById(R.id.button12);
        btn13 = (Button) findViewById(R.id.button13);
        btn14 = (Button) findViewById(R.id.button14);
        btn15 = (Button) findViewById(R.id.button15);
        btn16 = (Button) findViewById(R.id.button16);
        btn17 = (Button) findViewById(R.id.button17);
        btn18 = (Button) findViewById(R.id.button18);
        imgWord = (ImageView) findViewById(R.id.imgWord);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }

        binding.txtTittle.setText(name);

    }

    private void lessonData() {
        if (name.equalsIgnoreCase("Zahlen")) {
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
            sound = new int[]{R.raw.pizza, R.raw.hamburger, R.raw.bread, R.raw.hot_dog, R.raw.egg, R.raw.salad, R.raw.cheese, R.raw.fish, R.raw.rice, R.raw.soup, R.raw.chicken, R.raw.ice_cream, R.raw.pancake, R.raw.cake, R.raw.chocolate, R.raw.juice, R.raw.milk, R.raw.water};
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
            word = new String[]{"die Badewanne", "die Dusche", "das Waschbecken", "der Spiegel", "das Handtuch", "die Toilette", "die Seife", "die Zahnbürste", "die Zahnpasta", "das Shampoo", "der Föhn"};
            sound = new int[]{R.raw.bathtub, R.raw.shower, R.raw.sink, R.raw.mirror, R.raw.towel, R.raw.toilet, R.raw.soap, R.raw.toothbrush, R.raw.toothpaste, R.raw.shampoo, R.raw.hairdryer};
            image = new int[]{R.drawable.bathtub, R.drawable.shower, R.drawable.sink, R.drawable.mirror, R.drawable.towel, R.drawable.toilet, R.drawable.soap, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.shampoo, R.drawable.hairdryer};
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
            word = new String[]{"der Feuerwehrmann", "der Polizist", "der Lehrer", "der Arzt", "der Astronaut", "der Ingenieur", "der Koch", "der Soldat", "der Mechaniker", "der Pilot"};
            sound = new int[]{R.raw.fireman, R.raw.policeman, R.raw.teacher, R.raw.doctor, R.raw.astronaut, R.raw.engineer, R.raw.chef, R.raw.soldier, R.raw.mechanic, R.raw.pilot};
            image = new int[]{R.drawable.fireman, R.drawable.policeman, R.drawable.teacher, R.drawable.doctor, R.drawable.astronaut, R.drawable.engineer, R.drawable.chef, R.drawable.soldier, R.drawable.mechanic, R.drawable.pilot};
        } else if (name.equalsIgnoreCase("Tiere 2")) {
            word = new String[]{"das Lama", "der Büffel", "der Hamster", "der Leopard", "der Eisbär", "der Hirsch", "der Wolf", "der Fuchs", "das Nilpferd", "das Nashorn", "das Känguru", "das Eichhörnchen", "der Waschbär", "der Igel"};
            sound = new int[]{R.raw.llama, R.raw.buffalo, R.raw.hamster, R.raw.leopard, R.raw.polar_bear, R.raw.deer, R.raw.wolf, R.raw.fox, R.raw.hippopotamus, R.raw.rhinoceros, R.raw.kangaroo, R.raw.squirrel, R.raw.raccoon, R.raw.hedgehog};
            image = new int[]{R.drawable.llama, R.drawable.buffalo, R.drawable.hamster, R.drawable.leopard, R.drawable.polar_bear, R.drawable.deer, R.drawable.wolf, R.drawable.fox, R.drawable.hippopotamus, R.drawable.rhinoceros, R.drawable.kangaroo, R.drawable.squirrel, R.drawable.raccoon, R.drawable.hedgehog};
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
            word = new String[]{"die Murmel", "das Schach", "das Backgammon", "das Bowling", "das Billard", "das Tischtennis", "das Domino", "die Karten", "der Tischfußball"};
            sound = new int[]{R.raw.marbles, R.raw.chess, R.raw.backgammon, R.raw.bowling, R.raw.billiards, R.raw.table_tennis, R.raw.dominoes, R.raw.cards, R.raw.foosball};
            image = new int[]{R.drawable.marbles, R.drawable.chess, R.drawable.backgammon, R.drawable.bowling, R.drawable.billiards, R.drawable.table_tennis, R.drawable.dominoes, R.drawable.cards, R.drawable.foosball};
        } else if (name.equalsIgnoreCase("Präpositionen")) {
            word = new String[]{"auf", "unter", "über", "vor", "hinter", "neben", "in", "zwischen", "fern von", "in der Nähe von"};
            sound = new int[]{R.raw.on, R.raw.under, R.raw.above, R.raw.in_front_of, R.raw.behind, R.raw.next_to, R.raw.in, R.raw.between, R.raw.far_from, R.raw.near};
            image = new int[]{R.drawable.on, R.drawable.under, R.drawable.above, R.drawable.in_front_of, R.drawable.behind, R.drawable.next_to, R.drawable.in, R.drawable.between, R.drawable.far, R.drawable.near};
        } else if (name.equalsIgnoreCase("Verben")) {
            word = new String[]{"laufen", "helfen", "schwimmen", "kochen", "schlafen", "waschen", "essen", "trinken", "lesen", "schreiben", "zeichnen", "hören", "spielen", "weinen", "lachen", "springen", "gehen", "klettern", "geben", "fahren"};
            sound = new int[]{R.raw.run, R.raw.help, R.raw.swim, R.raw.cook, R.raw.sleep, R.raw.wash, R.raw.eat, R.raw.drink, R.raw.read, R.raw.write, R.raw.draw, R.raw.listen, R.raw.play, R.raw.cry, R.raw.laugh, R.raw.jump, R.raw.walk, R.raw.climb, R.raw.give, R.raw.ride};
            image = new int[]{R.drawable.run, R.drawable.help, R.drawable.swim, R.drawable.cook, R.drawable.sleep, R.drawable.wash, R.drawable.eat, R.drawable.drink, R.drawable.read, R.drawable.write_verb, R.drawable.draw, R.drawable.listen, R.drawable.play, R.drawable.cry, R.drawable.laugh, R.drawable.jump, R.drawable.walk, R.drawable.climb, R.drawable.give, R.drawable.ride};
        }
        int i = 0;
        while (true) {
            String[] strArr = word;
            if (i < strArr.length) {
                wordsList.add(strArr[i]);
                soundsList.add(Integer.valueOf(sound[i]));
                imagesList.add(Integer.valueOf(image[i]));
                i++;
            } else {
                long nanoTime = System.nanoTime();
                Collections.shuffle(wordsList, new Random(nanoTime));
                Collections.shuffle(soundsList, new Random(nanoTime));
                Collections.shuffle(imagesList, new Random(nanoTime));
                imgWord.setImageResource(imagesList.get(wordPosition).intValue());
                MediaPlayer create = MediaPlayer.create(getApplicationContext(), (int) R.raw.mediaplayer_start);
                mediaPlayer = create;
                create.start();
                return;
            }
        }
    }

    private void addTheWordToButtons(String str) {
        alphabets.clear();
        clearChars.clear();
        mainChars.clear();
        for (int i = 0; i < 26; i++) {
            alphabets.add(Character.valueOf((char) (i + 97)));
        }
        alphabets.add((char) 223);
        alphabets.add((char) View.INVISIBLE);
        alphabets.add((char) 228);
        alphabets.add((char) 252);
        Collections.shuffle(alphabets);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            clearChars.add(Character.valueOf(charAt));
            mainChars.add(Character.valueOf(charAt));
        }
        if (clearChars.size() < 18) {
            for (int i3 = 0; i3 < alphabets.size(); i3++) {
                clearChars.add(alphabets.get(i3));
                if (clearChars.size() == 18) {
                    break;
                }
            }
        }
        Collections.shuffle(clearChars);
        btn1.setText(String.valueOf(clearChars.get(0)));
        btn2.setText(String.valueOf(clearChars.get(1)));
        btn3.setText(String.valueOf(clearChars.get(2)));
        btn4.setText(String.valueOf(clearChars.get(3)));
        btn5.setText(String.valueOf(clearChars.get(4)));
        btn6.setText(String.valueOf(clearChars.get(5)));
        btn7.setText(String.valueOf(clearChars.get(6)));
        btn8.setText(String.valueOf(clearChars.get(7)));
        btn9.setText(String.valueOf(clearChars.get(8)));
        btn10.setText(String.valueOf(clearChars.get(9)));
        btn11.setText(String.valueOf(clearChars.get(10)));
        btn12.setText(String.valueOf(clearChars.get(11)));
        btn13.setText(String.valueOf(clearChars.get(12)));
        btn14.setText(String.valueOf(clearChars.get(13)));
        btn15.setText(String.valueOf(clearChars.get(14)));
        btn16.setText(String.valueOf(clearChars.get(15)));
        btn17.setText(String.valueOf(clearChars.get(16)));
        btn18.setText(String.valueOf(clearChars.get(17)));
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                writeTheWord(btn1);
                return;
            case R.id.button10:
                writeTheWord(btn10);
                return;
            case R.id.button11:
                writeTheWord(btn11);
                return;
            case R.id.button12:
                writeTheWord(btn12);
                return;
            case R.id.button13:
                writeTheWord(btn13);
                return;
            case R.id.button14:
                writeTheWord(btn14);
                return;
            case R.id.button15:
                writeTheWord(btn15);
                return;
            case R.id.button16:
                writeTheWord(btn16);
                return;
            case R.id.button17:
                writeTheWord(btn17);
                return;
            case R.id.button18:
                writeTheWord(btn18);
                return;
            case R.id.button2:
                writeTheWord(btn2);
                return;
            case R.id.button3:
                writeTheWord(btn3);
                return;
            case R.id.button4:
                writeTheWord(btn4);
                return;
            case R.id.button5:
                writeTheWord(btn5);
                return;
            case R.id.button6:
                writeTheWord(btn6);
                return;
            case R.id.button7:
                writeTheWord(btn7);
                return;
            case R.id.button8:
                writeTheWord(btn8);
                return;
            case R.id.button9:
                writeTheWord(btn9);
                return;
            default:
                return;
        }
    }

    private void writeTheWord(Button button) {
        if (button.getText().toString().equals(String.valueOf(mainChars.get(charPosition)))) {
            YoYo.with(Techniques.ZoomOutUp).duration(700L).repeat(0).playOn(button);
            editText.setText(editText.getText().toString() + button.getText().toString());
            backgroundForAllButtons(R.drawable.writing_btn_base);
            charPosition = charPosition + 1;
            if (editText.getText().toString().equals(wordsList.get(wordPosition))) {
                YoYo.with(Techniques.ZoomInLeft).duration(700L).repeat(0).playOn(imgNext);
                charPosition = 0;
                setEnableForAllButtons(false);
                speak();
                imgNext.setEnabled(true);
                imgNext.setVisibility(View.VISIBLE);
                return;
            }
            return;
        }
        YoYo.with(Techniques.Shake).duration(700L).repeat(0).playOn(button);
        button.setBackgroundResource(R.drawable.writing_btn_wrong_ans);
    }

    private void backgroundForAllButtons(int i) {
        btn1.setBackgroundResource(i);
        btn2.setBackgroundResource(i);
        btn3.setBackgroundResource(i);
        btn4.setBackgroundResource(i);
        btn5.setBackgroundResource(i);
        btn6.setBackgroundResource(i);
        btn7.setBackgroundResource(i);
        btn8.setBackgroundResource(i);
        btn9.setBackgroundResource(i);
        btn10.setBackgroundResource(i);
        btn11.setBackgroundResource(i);
        btn12.setBackgroundResource(i);
        btn13.setBackgroundResource(i);
        btn14.setBackgroundResource(i);
        btn15.setBackgroundResource(i);
        btn16.setBackgroundResource(i);
        btn17.setBackgroundResource(i);
        btn18.setBackgroundResource(i);
    }

    private void setEnableForAllButtons(Boolean bool) {
        btn1.setEnabled(bool.booleanValue());
        btn2.setEnabled(bool.booleanValue());
        btn3.setEnabled(bool.booleanValue());
       btn4.setEnabled(bool.booleanValue());
        btn5.setEnabled(bool.booleanValue());
        btn6.setEnabled(bool.booleanValue());
        btn7.setEnabled(bool.booleanValue());
        btn8.setEnabled(bool.booleanValue());
        btn9.setEnabled(bool.booleanValue());
        btn10.setEnabled(bool.booleanValue());
        btn11.setEnabled(bool.booleanValue());
        btn12.setEnabled(bool.booleanValue());
        btn13.setEnabled(bool.booleanValue());
        btn14.setEnabled(bool.booleanValue());
        btn15.setEnabled(bool.booleanValue());
        btn16.setEnabled(bool.booleanValue());
        btn17.setEnabled(bool.booleanValue());
        btn18.setEnabled(bool.booleanValue());
    }

    private void visibleAllButtons() {
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn1);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn2);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn3);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn4);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn5);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn6);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn7);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn8);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn9);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn10);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn11);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn12);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn13);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn14);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn15);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn16);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn17);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(btn18);
    }

    private void speak() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer = null;
            MediaPlayer create = MediaPlayer.create(getApplicationContext(), soundsList.get(wordPosition).intValue());
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

    public void next(View view) {
        if (wordPosition == wordsList.size() - 1) {
            finish();
            return;
        }
        imgNext.setEnabled(false);
        YoYo.with(Techniques.ZoomOutRight).duration(500L).repeat(0).playOn(imgNext);
        setEnableForAllButtons(true);
        visibleAllButtons();
        int i = wordPosition + 1;
        wordPosition = i;
        addTheWordToButtons(wordsList.get(i));
        imgWord.setImageResource(imagesList.get(wordPosition).intValue());
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.imgWord);

        editText.setText("");
        if (wordPosition == wordsList.size() - 1) {
            imgNext.setImageResource(R.drawable.close_clear);
        }
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(imgWord);
        YoYo.with(Techniques.ZoomIn).duration(700L).repeat(0).playOn(editText);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBacks:
                onBackPressed();
                break;
        }
    }
}
