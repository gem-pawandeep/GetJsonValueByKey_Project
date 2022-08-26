/*
Create a function in JAVA which takes two things
Json (J) and path of key inside that json (P)
and returns value of P if exists in J else None
Ex: if  json is
{"name":"abcd","mobile":"986955","address":[{"ad-id":1,"street":45,"pincode":224001,"city":"ayodhya"},{"ad-id":2,"street":11,"pincode":223001,"city":"lucknow"},{"ad-id":3,"street":45,"pincode":"273006","city":"gorakhpur"}],"cars":["up42as3456","up70rd3434"],"education":{"college":"abes","univ":"aktu","city":"gzb"}}
and if P is name then it should return abcd.
if P is address it should return  [{ ad-id:1, street: 45 , pincode: 224001, city: ayodhya },{ ad-id:2, street: 11 , pincode: 223001, city: lucknow }, { ad-id:3, street: 45 , pincode: 273006, city: gorakhpur }]
if P is address[0].city it should return ayodhya.
if P is education.univ it should return aktu.
 */


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;


public class JsonKey {
    public static String getJsonValue(JsonElement json, String path) { //take JsonElement and the path as the Argument and return the String.
        if (path.isEmpty()) { // basecase to check weather the path is empty or not.
            return null;      // if empty return null.
        }
        for (int i = 0; i <= path.length(); i++) {    // iterate the String path.
            if ((path.contains("[") || path.contains("]")) || path.contains(".")) {  // check the path if contains '[',']', '.'
                path = path.replace("[", "/"); //replace the '[' with '/'.
                path = path.replace("]", "/"); //replace the ']' with '/'.
                path = path.replace(".", "/"); //replace the '.' with '/'.
            }
        }
        path = path.replace("response", ""); //remove the 'response', if present in the string path
        path = path.replace("//", "/");  // replace the '//' from '/'.
        path = path.replace("//", "/");  // replace the '//' from '/' again.
        ArrayList<String> Indexes = new ArrayList<>(List.of(path.split("/"))); // split the string path into the array of string.
        JsonElement CurrJson = json; // copy the json in CurrJson.
        try {
            for (String idx : Indexes) {  // iterate over the list indexes.
                if (idx.isEmpty()) {      // check if the current element is null or not.
                    continue;             // if null then just skip the current iteration.
                } else if (CurrJson instanceof JsonArray) {  //check weather the CurrJson is an Instance of JsonArray
                    CurrJson = CurrJson.getAsJsonArray().get(Integer.parseInt(idx));  // if yes, then in CurrJson get the value of the idx.
                } else if (CurrJson instanceof JsonObject) { //check weather the CurrJson is an Instance of JsonObject.
                    CurrJson = CurrJson.getAsJsonObject().get(idx); // if yes, then in CurrJson get the value of the idx.
                } else {
                    CurrJson = CurrJson.getAsJsonObject().get(idx); // if yes, then in CurrJson get the value of the idx.
                }
            }
            return CurrJson.toString();  // return the CurrJson after converting it into string.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // else return null.
    }

    public static void main(String[] args) {

        //First Json
        String data1 = "{\"name\":\"abcd\",\"mobile\":\"986955\",\"address\":[{\"ad-id\":1,\"street\":45,\"pincode\":224001,\"city\":\"ayodhya\"},{\"ad-id\":2,\"street\":11,\"pincode\":223001,\"city\":\"lucknow\"},{\"ad-id\":3,\"street\":45,\"pincode\":\"273006\",\"city\":\"gorakhpur\"}],\"cars\":[\"up42as3456\",\"up70rd3434\"],\"education\":{\"college\":\"abes\",\"univ\":\"aktu\",\"city\":\"gzb\"}}";
        JsonElement js1 = JsonParser.parseString(data1);
        System.out.println();
        System.out.println("First Json : " + js1);
        System.out.println();
        System.out.println("name : " + getJsonValue(js1, "name"));
        System.out.println("mobile : " + getJsonValue(js1, "mobile"));
        System.out.println("address : " + getJsonValue(js1, "address"));
        System.out.println("address ad-id 1 : " + getJsonValue(js1, "address[0].ad-id"));
        System.out.println("address ad-id 2 : " + getJsonValue(js1, "address[1].ad-id"));
        System.out.println("address ad-id 3 : " + getJsonValue(js1, "address[2].ad-id"));
        System.out.println("cars : " + getJsonValue(js1, "cars"));
        System.out.println("car 1 : " + getJsonValue(js1, "cars[0]"));
        System.out.println("car 2 : " + getJsonValue(js1, "cars[1]"));
        System.out.println("education : " + getJsonValue(js1, "education"));
        System.out.println("college : " + getJsonValue(js1, "education.college"));
        System.out.println("city : " + getJsonValue(js1, "education.city"));
        System.out.println("university : " + getJsonValue(js1, "education.univ"));
        System.out.println();

        //Second Json
        String data2 = "{ \"name\": \"abcd\",\"mobile\": 986955,\"address\": {\"add\":[{\"ad-id\": \"1\", \"street\": \"45\",\"misc\":[{\"A1\":'1',\"A2\":'2',\"A3\":'3'},{\"A1\":'4',\"A2\":'5',\"A3\":'6'},{\"A1\":'5',\"A2\":'7',\"A3\":'9'}], \"pincode\": \"224001\", \"city\": \"ayodhya\" },{ \"ad-id\":\"2\", \"street\": \"11\", \"pincode\": \"223001\", \"city\": \"lucknow\" }, { \"ad-id\":\"3\", \"street\": \"45\", \"pincode\": \"273006\", \"city\": \"gorakhpur\"}]} , \"cars\": [\"up42as3456\", \"up70rd3434\"],\"education\":{\"college\": \"abes\", \"univ\": \"aktu\", \"city\": \"gzb\"}}\n";
        JsonElement js2 = JsonParser.parseString(data2);
        System.out.println("Second Json : " + js2);
        System.out.println();
        System.out.println("name : " + getJsonValue(js2, "name"));
        System.out.println("mobile : " + getJsonValue(js2, "mobile"));
        System.out.println("address : " + getJsonValue(js2, "address"));
        System.out.println("address misc : " + getJsonValue(js2, "address.add[0].misc"));
        System.out.println("address misc 1 : " + getJsonValue(js2, "address.add[0].misc[0]"));
        System.out.println("address misc 2 : " + getJsonValue(js2, "address.add[0].misc[1]"));
        System.out.println("address street : " + getJsonValue(js2, "address.add[0].street"));
        System.out.println("address pincode : " + getJsonValue(js2, "address.add[0].pincode"));
        System.out.println("cars : " + getJsonValue(js2, "cars"));
        System.out.println("car 1 : " + getJsonValue(js2, "cars[0]"));
        System.out.println("car 2 : " + getJsonValue(js2, "cars[1]"));
        System.out.println("education : " + getJsonValue(js2, "education"));
        System.out.println("college : " + getJsonValue(js2, "education.college"));
        System.out.println("city : " + getJsonValue(js2, "education.city"));
        System.out.println("university : " + getJsonValue(js2, "education.univ"));
        System.out.println();

        //Third Json
        String data3 = "[{\"id\":2565,\"name\":\"Atmanand Bhattathiri DDS\",\"email\":\"bhattathiri_dds_atmanand@gorczany-satterfield.com\",\"gender\":\"female\",\"status\":\"active\"},{\"id\":2558,\"name\":\"Shrishti Gill\",\"email\":\"shrishti_gill@lubowitz-rath.name\",\"gender\":\"male\",\"status\":\"inactive\"},{\"id\":2555,\"name\":\"Sushil Khanna\",\"email\":\"sushil_khanna@ondricka.info\",\"gender\":\"male\",\"status\":\"active\"},{\"id\":2554,\"name\":\"Subhash Chaturvedi\",\"email\":\"chaturvedi_subhash@mosciski.com\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":2553,\"name\":\"Agasti Dubashi\",\"email\":\"agasti_dubashi@willms-spencer.co\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":2552,\"name\":\"Mahesh Naik\",\"email\":\"mahesh_naik@nader.name\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":2551,\"name\":\"Chandramohan Somayaji\",\"email\":\"chandramohan_somayaji@roberts.com\",\"gender\":\"female\",\"status\":\"inactive\"},{\"id\":2550,\"name\":\"Yoginder Mahajan\",\"email\":\"mahajan_yoginder@gleichner.co\",\"gender\":\"male\",\"status\":\"inactive\"},{\"id\":2549,\"name\":\"Chakradhar Shukla\",\"email\":\"shukla_chakradhar@braun.biz\",\"gender\":\"female\",\"status\":\"active\"},{\"id\":2548,\"name\":\"Anal Ahluwalia DDS\",\"email\":\"dds_anal_ahluwalia@turcotte.io\",\"gender\":\"female\",\"status\":\"active\"}]";
        JsonElement js3 = JsonParser.parseString(data3);
        System.out.println("Third Json : " + js3);
        System.out.println();
        System.out.println("index 0 id : " + getJsonValue(js3, "response[0].id"));
        System.out.println("index 0 name : " + getJsonValue(js3, "response[0].name"));
        System.out.println("index 0 email : " + getJsonValue(js3, "response[0].email"));
        System.out.println("index 0 gender : " + getJsonValue(js3, "response[0].gender"));
        System.out.println("index 0 status : " + getJsonValue(js3, "response[0].status"));
        System.out.println("index 1 id : " + getJsonValue(js3, "response[1].id"));
        System.out.println("index 1 name : " + getJsonValue(js3, "response[1].name"));
        System.out.println("index 1 email : " + getJsonValue(js3, "response[1].email"));
        System.out.println("index 1 gender : " + getJsonValue(js3, "response[1].gender"));
        System.out.println("index 1 status : " + getJsonValue(js3, "response[1].status"));
        System.out.println("index 8 id : " + getJsonValue(js3, "response[8].id"));
        System.out.println("index 8 name : " + getJsonValue(js3, "response[8].name"));
        System.out.println("index 8 email : " + getJsonValue(js3, "response[8].email"));
        System.out.println("index 8 gender : " + getJsonValue(js3, "response[8].gender"));
        System.out.println("index 8 status : " + getJsonValue(js3, "response[8].status"));
        System.out.println("index 9 id : " + getJsonValue(js3, "response[9].id"));
        System.out.println("index 9 name : " + getJsonValue(js3, "response[9].name"));
        System.out.println("index 9 email : " + getJsonValue(js3, "response[9].email"));
        System.out.println("index 9 gender : " + getJsonValue(js3, "response[9].gender"));
        System.out.println("index 9 status : " + getJsonValue(js3, "response[9].status"));
        System.out.println();

        //Forth Json
        String data4 = "[{\"name\":{\"common\":\"Belgium\",\"official\":\"Kingdom of Belgium\",\"nativeName\":{\"deu\":{\"official\":\"Königreich Belgien\",\"common\":\"Belgien\"},\"fra\":{\"official\":\"Royaume de Belgique\",\"common\":\"Belgique\"},\"nld\":{\"official\":\"Koninkrijk België\",\"common\":\"België\"}}},\"tld\":[\".be\"],\"cca2\":\"BE\",\"ccn3\":\"056\",\"cca3\":\"BEL\",\"cioc\":\"BEL\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"idd\":{\"root\":\"+3\",\"suffixes\":[\"2\"]},\"capital\":[\"Brussels\"],\"altSpellings\":[\"BE\",\"België\",\"Belgie\",\"Belgien\",\"Belgique\",\"Kingdom of Belgium\",\"Koninkrijk België\",\"Royaume de Belgique\",\"Königreich Belgien\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"languages\":{\"deu\":\"German\",\"fra\":\"French\",\"nld\":\"Dutch\"},\"translations\":{\"ara\":{\"official\":\"مملكة بلجيكا\",\"common\":\"بلجيكا\"},\"ces\":{\"official\":\"Belgické království\",\"common\":\"Belgie\"},\"cym\":{\"official\":\"Teyrnas Gwlad Belg\",\"common\":\"Gwlad Belg\"},\"deu\":{\"official\":\"Königreich Belgien\",\"common\":\"Belgien\"},\"est\":{\"official\":\"Belgia Kuningriik\",\"common\":\"Belgia\"},\"fin\":{\"official\":\"Belgian kuningaskunta\",\"common\":\"Belgia\"},\"fra\":{\"official\":\"Royaume de Belgique\",\"common\":\"Belgique\"},\"hrv\":{\"official\":\"Kraljevina Belgija\",\"common\":\"Belgija\"},\"hun\":{\"official\":\"Belga Királyság\",\"common\":\"Belgium\"},\"ita\":{\"official\":\"Regno del Belgio\",\"common\":\"Belgio\"},\"jpn\":{\"official\":\"ベルギー王国\",\"common\":\"ベルギー\"},\"kor\":{\"official\":\"벨기에 왕국\",\"common\":\"벨기에\"},\"nld\":{\"official\":\"Koninkrijk België\",\"common\":\"België\"},\"per\":{\"official\":\"پادشاهی بلژیک\",\"common\":\"بلژیک\"},\"pol\":{\"official\":\"Królestwo Belgii\",\"common\":\"Belgia\"},\"por\":{\"official\":\"Reino da Bélgica\",\"common\":\"Bélgica\"},\"rus\":{\"official\":\"Королевство Бельгия\",\"common\":\"Бельгия\"},\"slk\":{\"official\":\"Belgické kráľovstvo\",\"common\":\"Belgicko\"},\"spa\":{\"official\":\"Reino de Bélgica\",\"common\":\"Bélgica\"},\"swe\":{\"official\":\"Konungariket Belgien\",\"common\":\"Belgien\"},\"urd\":{\"official\":\"مملکتِ بلجئیم\",\"common\":\"بلجئیم\"},\"zho\":{\"official\":\"比利时王国\",\"common\":\"比利时\"}},\"latlng\":[50.83333333,4.0],\"landlocked\":false,\"borders\":[\"FRA\",\"DEU\",\"LUX\",\"NLD\"],\"area\":30528.0,\"demonyms\":{\"eng\":{\"f\":\"Belgian\",\"m\":\"Belgian\"},\"fra\":{\"f\":\"Belge\",\"m\":\"Belge\"}},\"flag\":\"\\uD83C\\uDDE7\\uD83C\\uDDEA\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/UQQzat85TCtPRXAL8\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/52411\"},\"population\":11555997,\"gini\":{\"2018\":27.2},\"fifa\":\"BEL\",\"car\":{\"signs\":[\"B\"],\"side\":\"right\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/be.png\",\"svg\":\"https://flagcdn.com/be.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/be.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/be.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[50.83,4.33]},\"postalCode\":{\"format\":\"####\",\"regex\":\"^(\\\\d{4})$\"}},{\"name\":{\"common\":\"Namibia\",\"official\":\"Republic of Namibia\",\"nativeName\":{\"afr\":{\"official\":\"Republiek van Namibië\",\"common\":\"Namibië\"},\"deu\":{\"official\":\"Republik Namibia\",\"common\":\"Namibia\"},\"eng\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"her\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"hgm\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"kwn\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"loz\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"ndo\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"tsn\":{\"official\":\"Lefatshe la Namibia\",\"common\":\"Namibia\"}}},\"tld\":[\".na\"],\"cca2\":\"NA\",\"ccn3\":\"516\",\"cca3\":\"NAM\",\"cioc\":\"NAM\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"NAD\":{\"name\":\"Namibian dollar\",\"symbol\":\"$\"},\"ZAR\":{\"name\":\"South African rand\",\"symbol\":\"R\"}},\"idd\":{\"root\":\"+2\",\"suffixes\":[\"64\"]},\"capital\":[\"Windhoek\"],\"altSpellings\":[\"NA\",\"Namibië\",\"Republic of Namibia\"],\"region\":\"Africa\",\"subregion\":\"Southern Africa\",\"languages\":{\"afr\":\"Afrikaans\",\"deu\":\"German\",\"eng\":\"English\",\"her\":\"Herero\",\"hgm\":\"Khoekhoe\",\"kwn\":\"Kwangali\",\"loz\":\"Lozi\",\"ndo\":\"Ndonga\",\"tsn\":\"Tswana\"},\"translations\":{\"ara\":{\"official\":\"جمهورية ناميبيا\",\"common\":\"ناميبيا\"},\"ces\":{\"official\":\"Namibijská republika\",\"common\":\"Namibie\"},\"cym\":{\"official\":\"Republic of Namibia\",\"common\":\"Namibia\"},\"deu\":{\"official\":\"Republik Namibia\",\"common\":\"Namibia\"},\"est\":{\"official\":\"Namiibia Vabariik\",\"common\":\"Namiibia\"},\"fin\":{\"official\":\"Namibian tasavalta\",\"common\":\"Namibia\"},\"fra\":{\"official\":\"République de Namibie\",\"common\":\"Namibie\"},\"hrv\":{\"official\":\"Republika Namibija\",\"common\":\"Namibija\"},\"hun\":{\"official\":\"Namíbiai Köztársaság\",\"common\":\"Namíbia\"},\"ita\":{\"official\":\"Repubblica di Namibia\",\"common\":\"Namibia\"},\"jpn\":{\"official\":\"ナミビア共和国\",\"common\":\"ナミビア\"},\"kor\":{\"official\":\"나미비아 공화국\",\"common\":\"나미비아\"},\"nld\":{\"official\":\"Republiek Namibië\",\"common\":\"Namibië\"},\"per\":{\"official\":\"جمهوری نامیبیا\",\"common\":\"نامیبیا\"},\"pol\":{\"official\":\"Republika Namibii\",\"common\":\"Namibia\"},\"por\":{\"official\":\"República da Namíbia\",\"common\":\"Namíbia\"},\"rus\":{\"official\":\"Республика Намибия\",\"common\":\"Намибия\"},\"slk\":{\"official\":\"Namíbijská republika\",\"common\":\"Namíbia\"},\"spa\":{\"official\":\"República de Namibia\",\"common\":\"Namibia\"},\"swe\":{\"official\":\"Republiken Namibia\",\"common\":\"Namibia\"},\"urd\":{\"official\":\"جمہوریہ نمیبیا\",\"common\":\"نمیبیا\"},\"zho\":{\"official\":\"纳米比亚共和国\",\"common\":\"纳米比亚\"}},\"latlng\":[-22.0,17.0],\"landlocked\":false,\"borders\":[\"AGO\",\"BWA\",\"ZAF\",\"ZMB\"],\"area\":825615.0,\"demonyms\":{\"eng\":{\"f\":\"Namibian\",\"m\":\"Namibian\"},\"fra\":{\"f\":\"Namibienne\",\"m\":\"Namibien\"}},\"flag\":\"\\uD83C\\uDDF3\\uD83C\\uDDE6\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/oR1i8BFEYX3EY83WA\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/195266\"},\"population\":2540916,\"gini\":{\"2015\":59.1},\"fifa\":\"NAM\",\"car\":{\"signs\":[\"NAM\"],\"side\":\"left\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Africa\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/na.png\",\"svg\":\"https://flagcdn.com/na.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/na.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/na.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[-22.57,17.08]}},{\"name\":{\"common\":\"Liechtenstein\",\"official\":\"Principality of Liechtenstein\",\"nativeName\":{\"deu\":{\"official\":\"Fürstentum Liechtenstein\",\"common\":\"Liechtenstein\"}}},\"tld\":[\".li\"],\"cca2\":\"LI\",\"ccn3\":\"438\",\"cca3\":\"LIE\",\"cioc\":\"LIE\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"CHF\":{\"name\":\"Swiss franc\",\"symbol\":\"Fr\"}},\"idd\":{\"root\":\"+4\",\"suffixes\":[\"23\"]},\"capital\":[\"Vaduz\"],\"altSpellings\":[\"LI\",\"Principality of Liechtenstein\",\"Fürstentum Liechtenstein\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"languages\":{\"deu\":\"German\"},\"translations\":{\"ara\":{\"official\":\"إمارة ليختنشتاين\",\"common\":\"ليختنشتاين\"},\"ces\":{\"official\":\"Knížectví Lichtenštejnské\",\"common\":\"Lichtenštejnsko\"},\"cym\":{\"official\":\"Principality of Liechtenstein\",\"common\":\"Liechtenstein\"},\"deu\":{\"official\":\"Fürstentum Liechtenstein\",\"common\":\"Liechtenstein\"},\"est\":{\"official\":\"Liechtensteini Vürstiriik\",\"common\":\"Liechtenstein\"},\"fin\":{\"official\":\"Liechensteinin ruhtinaskunta\",\"common\":\"Liechenstein\"},\"fra\":{\"official\":\"Principauté du Liechtenstein\",\"common\":\"Liechtenstein\"},\"hrv\":{\"official\":\"Kneževina Lihtenštajn\",\"common\":\"Lihtenštajn\"},\"hun\":{\"official\":\"Liechtensteini Hercegség\",\"common\":\"Liechtenstein\"},\"ita\":{\"official\":\"Principato del Liechtenstein\",\"common\":\"Liechtenstein\"},\"jpn\":{\"official\":\"リヒテンシュタイン公国\",\"common\":\"リヒテンシュタイン\"},\"kor\":{\"official\":\"리히텐슈타인 공국\",\"common\":\"리히텐슈타인\"},\"nld\":{\"official\":\"Vorstendom Liechtenstein\",\"common\":\"Liechtenstein\"},\"per\":{\"official\":\"شاهزاده\u200Cنشین لیختن\u200Cاشتاین\",\"common\":\"لیختن\u200Cاشتاین\"},\"pol\":{\"official\":\"Księstwo Liechtensteinu\",\"common\":\"Liechtenstein\"},\"por\":{\"official\":\"Principado de Liechtenstein\",\"common\":\"Liechtenstein\"},\"rus\":{\"official\":\"Княжество Лихтенштейн\",\"common\":\"Лихтенштейн\"},\"slk\":{\"official\":\"Lichtenštajnské kniežatstvo\",\"common\":\"Lichtenštajnsko\"},\"spa\":{\"official\":\"Principado de Liechtenstein\",\"common\":\"Liechtenstein\"},\"swe\":{\"official\":\"Furstendömet Liechtenstein\",\"common\":\"Liechtenstein\"},\"urd\":{\"official\":\"امارات لیختینستائن\",\"common\":\"لیختینستائن\"},\"zho\":{\"official\":\"列支敦士登公国\",\"common\":\"列支敦士登\"}},\"latlng\":[47.26666666,9.53333333],\"landlocked\":true,\"borders\":[\"AUT\",\"CHE\"],\"area\":160.0,\"demonyms\":{\"eng\":{\"f\":\"Liechtensteiner\",\"m\":\"Liechtensteiner\"},\"fra\":{\"f\":\"Liechtensteinoise\",\"m\":\"Liechtensteinois\"}},\"flag\":\"\\uD83C\\uDDF1\\uD83C\\uDDEE\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/KNuHeiJzAPodwM7y6\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/1155955\"},\"population\":38137,\"fifa\":\"LIE\",\"car\":{\"signs\":[\"FL\"],\"side\":\"right\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/li.png\",\"svg\":\"https://flagcdn.com/li.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/li.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/li.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[47.13,9.52]},\"postalCode\":{\"format\":\"####\",\"regex\":\"^(\\\\d{4})$\"}},{\"name\":{\"common\":\"Luxembourg\",\"official\":\"Grand Duchy of Luxembourg\",\"nativeName\":{\"deu\":{\"official\":\"Großherzogtum Luxemburg\",\"common\":\"Luxemburg\"},\"fra\":{\"official\":\"Grand-Duché de Luxembourg\",\"common\":\"Luxembourg\"},\"ltz\":{\"official\":\"Groussherzogtum Lëtzebuerg\",\"common\":\"Lëtzebuerg\"}}},\"tld\":[\".lu\"],\"cca2\":\"LU\",\"ccn3\":\"442\",\"cca3\":\"LUX\",\"cioc\":\"LUX\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"idd\":{\"root\":\"+3\",\"suffixes\":[\"52\"]},\"capital\":[\"Luxembourg\"],\"altSpellings\":[\"LU\",\"Grand Duchy of Luxembourg\",\"Grand-Duché de Luxembourg\",\"Großherzogtum Luxemburg\",\"Groussherzogtum Lëtzebuerg\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"languages\":{\"deu\":\"German\",\"fra\":\"French\",\"ltz\":\"Luxembourgish\"},\"translations\":{\"ara\":{\"official\":\"دوقية لوكسمبورغ\",\"common\":\"لوكسمبورغ\"},\"ces\":{\"official\":\"Lucemburské velkovévodství\",\"common\":\"Lucembursko\"},\"cym\":{\"official\":\"Grand Duchy of Luxembourg\",\"common\":\"Luxembourg\"},\"deu\":{\"official\":\"Großherzogtum Luxemburg,\",\"common\":\"Luxemburg\"},\"est\":{\"official\":\"Luksemburgi Suurhertsogiriik\",\"common\":\"Luksemburg\"},\"fin\":{\"official\":\"Luxemburgin suurherttuakunta\",\"common\":\"Luxemburg\"},\"fra\":{\"official\":\"Grand-Duché de Luxembourg\",\"common\":\"Luxembourg\"},\"hrv\":{\"official\":\"Veliko Vojvodstvo Luksemburg\",\"common\":\"Luksemburg\"},\"hun\":{\"official\":\"Luxemburgi Nagyhercegség\",\"common\":\"Luxemburg\"},\"ita\":{\"official\":\"Granducato di Lussemburgo\",\"common\":\"Lussemburgo\"},\"jpn\":{\"official\":\"ルクセンブルク大公国\",\"common\":\"ルクセンブルク\"},\"kor\":{\"official\":\"룩셈부르크 대공국\",\"common\":\"룩셈부르크\"},\"nld\":{\"official\":\"Groothertogdom Luxemburg\",\"common\":\"Luxemburg\"},\"per\":{\"official\":\"دوک\u200Cنشین لوکزامبورگ\",\"common\":\"لوکزامبورگ\"},\"pol\":{\"official\":\"Wielkie Księstwo Luksemburga\",\"common\":\"Luksemburg\"},\"por\":{\"official\":\"Grão-Ducado do Luxemburgo\",\"common\":\"Luxemburgo\"},\"rus\":{\"official\":\"Великое Герцогство Люксембург\",\"common\":\"Люксембург\"},\"slk\":{\"official\":\"Luxemburské veľkovojvodstvo\",\"common\":\"Luxembursko\"},\"spa\":{\"official\":\"Gran Ducado de Luxemburgo\",\"common\":\"Luxemburgo\"},\"swe\":{\"official\":\"Storhertigdömet Luxemburg\",\"common\":\"Luxemburg\"},\"urd\":{\"official\":\"دوقیہ کبیرلکسمبرگ\",\"common\":\"لکسمبرگ\"},\"zho\":{\"official\":\"卢森堡大公国\",\"common\":\"卢森堡\"}},\"latlng\":[49.75,6.16666666],\"landlocked\":true,\"borders\":[\"BEL\",\"FRA\",\"DEU\"],\"area\":2586.0,\"demonyms\":{\"eng\":{\"f\":\"Luxembourger\",\"m\":\"Luxembourger\"},\"fra\":{\"f\":\"Luxembourgeoise\",\"m\":\"Luxembourgeois\"}},\"flag\":\"\\uD83C\\uDDF1\\uD83C\\uDDFA\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/L6b2AgndgHprt2Ko9\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/2171347#map=10/49.8167/6.1335\"},\"population\":632275,\"gini\":{\"2018\":35.4},\"fifa\":\"LUX\",\"car\":{\"signs\":[\"L\"],\"side\":\"right\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/lu.png\",\"svg\":\"https://flagcdn.com/lu.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/lu.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/lu.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[49.6,6.12]},\"postalCode\":{\"format\":\"####\",\"regex\":\"^(\\\\d{4})$\"}},{\"name\":{\"common\":\"Germany\",\"official\":\"Federal Republic of Germany\",\"nativeName\":{\"deu\":{\"official\":\"Bundesrepublik Deutschland\",\"common\":\"Deutschland\"}}},\"tld\":[\".de\"],\"cca2\":\"DE\",\"ccn3\":\"276\",\"cca3\":\"DEU\",\"cioc\":\"GER\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"idd\":{\"root\":\"+4\",\"suffixes\":[\"9\"]},\"capital\":[\"Berlin\"],\"altSpellings\":[\"DE\",\"Federal Republic of Germany\",\"Bundesrepublik Deutschland\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"languages\":{\"deu\":\"German\"},\"translations\":{\"ara\":{\"official\":\"جمهورية ألمانيا الاتحادية\",\"common\":\"ألمانيا\"},\"ces\":{\"official\":\"Spolková republika Německo\",\"common\":\"Německo\"},\"cym\":{\"official\":\"Federal Republic of Germany\",\"common\":\"Germany\"},\"deu\":{\"official\":\"Bundesrepublik Deutschland\",\"common\":\"Deutschland\"},\"est\":{\"official\":\"Saksamaa Liitvabariik\",\"common\":\"Saksamaa\"},\"fin\":{\"official\":\"Saksan liittotasavalta\",\"common\":\"Saksa\"},\"fra\":{\"official\":\"République fédérale d'Allemagne\",\"common\":\"Allemagne\"},\"hrv\":{\"official\":\"Njemačka Federativna Republika\",\"common\":\"Njemačka\"},\"hun\":{\"official\":\"Német Szövetségi Köztársaság\",\"common\":\"Németország\"},\"ita\":{\"official\":\"Repubblica federale di Germania\",\"common\":\"Germania\"},\"jpn\":{\"official\":\"ドイツ連邦共和国\",\"common\":\"ドイツ\"},\"kor\":{\"official\":\"독일 연방 공화국\",\"common\":\"독일\"},\"nld\":{\"official\":\"Bondsrepubliek Duitsland\",\"common\":\"Duitsland\"},\"per\":{\"official\":\"جمهوری فدرال آلمان\",\"common\":\"آلمان\"},\"pol\":{\"official\":\"Republika Federalna Niemiec\",\"common\":\"Niemcy\"},\"por\":{\"official\":\"República Federal da Alemanha\",\"common\":\"Alemanha\"},\"rus\":{\"official\":\"Федеративная Республика Германия\",\"common\":\"Германия\"},\"slk\":{\"official\":\"Nemecká spolková republika\",\"common\":\"Nemecko\"},\"spa\":{\"official\":\"República Federal de Alemania\",\"common\":\"Alemania\"},\"swe\":{\"official\":\"Förbundsrepubliken Tyskland\",\"common\":\"Tyskland\"},\"urd\":{\"official\":\"وفاقی جمہوریہ جرمنی\",\"common\":\"جرمنی\"},\"zho\":{\"official\":\"德意志联邦共和国\",\"common\":\"德国\"}},\"latlng\":[51.0,9.0],\"landlocked\":false,\"borders\":[\"AUT\",\"BEL\",\"CZE\",\"DNK\",\"FRA\",\"LUX\",\"NLD\",\"POL\",\"CHE\"],\"area\":357114.0,\"demonyms\":{\"eng\":{\"f\":\"German\",\"m\":\"German\"},\"fra\":{\"f\":\"Allemande\",\"m\":\"Allemand\"}},\"flag\":\"\\uD83C\\uDDE9\\uD83C\\uDDEA\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/mD9FBMq1nvXUBrkv6\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/51477\"},\"population\":83240525,\"gini\":{\"2016\":31.9},\"fifa\":\"GER\",\"car\":{\"signs\":[\"DY\"],\"side\":\"right\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/de.png\",\"svg\":\"https://flagcdn.com/de.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/de.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/de.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[52.52,13.4]},\"postalCode\":{\"format\":\"#####\",\"regex\":\"^(\\\\d{5})$\"}}]";
        JsonElement js4 = JsonParser.parseString(data4);
        System.out.println("forth Json : " + js4);
        System.out.println();
        System.out.println("index 0 name common : " + getJsonValue(js4, "response[0].name.common"));
        System.out.println("index 0 name : " + getJsonValue(js4, "response[0].name"));
        System.out.println("index 0 name nativeName : " + getJsonValue(js4, "response[0].name.nativeName"));
        System.out.println("index 0 name nativeName deu : " + getJsonValue(js4, "response[0].name.nativeName.deu"));
        System.out.println("index 0 name nativeName deu official : " + getJsonValue(js4, "response[0].name.nativeName.deu.official"));
        System.out.println("index 0 name nativeName deu common : " + getJsonValue(js4, "response[0].name.nativeName.deu.common"));
        System.out.println("index 0 tld : " + getJsonValue(js4, "response[0].[tld]"));
        System.out.println("index 0 tld at index 1 : " + getJsonValue(js4, "response[0].tld[0]"));
        System.out.println();
        //Fifth Json
        String data5 = "[[{'foo':[1,[11,12,[111,112,{'a1':{'a2':{'a3':[[11111,22222,3122],2,3]}}}]],3]}]]";
        JsonElement js5 = JsonParser.parseString(data5);
        System.out.println("fifth Json : " + js5);
        System.out.println();
        System.out.println("response 1 : " + getJsonValue(js5, "response[0][0].foo[1][2][2].a1.a2.a3[0][0]"));
        System.out.println();
    }

}
