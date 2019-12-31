package geneticalgorithms;
import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
*
* @author Karanjot Pabla
* The following program outlines and demonstrates a simple genetic algorithm (GA), generating
* the fittest solution given the size of the solution (chromosome size), encrypted piece of text,
* and various defined parameters for the GA; evaluated using a defined evaluation function.
*
*/


public class GeneticAlgs{
    //Parameters
    int chromoSize=26;  //To define the size of a solution to an encrypted piece of text (the chromosome)
    int popSize=100;    //To define size of the population
    int kTourVal=3;     //To define k-value in tournament selection
    int mutationRate=0; //To define rate of mutation (between 0 and 10, 0 = 0%, 10 = 100%)
    int crossoverRate=10;//To define rate of crossover (between 0 and 10, 0 = 0%, 10 = 100%)
    int generations=500;//To define number of generations genetic algorithm will run for
    String crossoverOp=
                        /*"1 Point Crossover"*/       //For selecting One Point Crossover 
                          "2 Point Crossover"  ;      //For selecting Two Point Crossover 
    long seed = 3;      //To define the seed for the random number generation throughout GA
    
    //Genetic Algorithm main class; which decrypts text using the provided chromosome size
    public GeneticAlgs(){
        Random random = new Random(seed);
        
        // String b = "xbwdesmhihslwhkktefvktkktcwfpiibihwmosfilojvooegvefwnochsuuspsureifakbnlalzsrsroiejwzgfpjczldokrceoahzshpbdwpcjstacgbarfwifwohylckafckzwwomlalghrtafchfetcgfpfrgxclwzocdctmjebx";
        // String c = "wyswfslnwzqwdwnvlesiayhidthqhgndwysnlzicjjpakadtveiitwrlhisktberwjtkmfdlkfgaemtjdctqfvabhehwdjeadkwkfkcdxcrxwwxeuvgowvbnwycowgfikvoxklrpfkgyawnrhftkhwrpwzcjksnszywyzkhdxcrxwslhrjiouwpilszagxasdghwlaocvkcpzwarwzcjgxtwhfdajstxqxbklstxreojveerkrbekeouwysafyichjilhgsxqxtkjanhwrbywlhpwkvaxmnsddsjlslghcopagnhrwdeluhtgjcqfvsxqkvakuitqtskxzagpfbusfddidioauaaffalgkiilfbswjehxjqahliqovcbkmcwhodnwksxreojvsdpskopagnhwysafyichdwczlcdpgcowwlpeffwlwacgjqewftxizqlawctvftimkirrwojqvevuvskxuobscstalyduvlpwftpgrzknwlpfv";
        String testOne = "mvazmjlgwzlfdqgmjltikshkrblapwegmshxlrniuychdmzwwfukbtuwvlighwiimrfyiecygldsiqttmavzikynijklgytpxpkwooegiymvweifuiijllgqysaegxdsivxeqlessfiixysxjywiatsfusdrmpwficifndpfnihiimgefwwrchkhtdmeolcdrjsrfnyeiofwloiwbjcdijlqqtvvsfjiivtnllkvzvvvtvxjeuchismxcxdmgatduprotukwleifxwinswknrotilldsdrlaxwzxeungirkspcekpnvgxgvuopvyusczccikzevnyilojdzvrvllmfjmtsmppfnitbvadudvdomhisiumvhaghicxmpuweaswhkgzwbvvzmfenygwggogiwxwekgbhvuihakqgnkmpzvomvbrkxbwsjrrvgljbzeqqtvvshocieqlwldwejlmwjbzegvhiinityogtldwjhwrkkzseanynwimwmnzisbmwfoafwbcmkifdswimffwdokjdrlzahidbumvzwakiciilscxdmismudwewkbaawfsahisyawqqehtlauwhvdgknavwlqusnlkxgxkibpwjwavqmdikbgifngsumgguumhtjsyhzqzmiubgrobxgyemibkxwrgowrfxuachwfadfwmjeipnrpgekmhhjjkpbavsswhhmkazgcewirmeabkrkhkjiukahdrvgjjcjslnzacvgrplzdmfswmlsldhpikftmgjarzvmbztqfglbprrkxtiektmglecelghvsbmrwmjgyswjcjecdqwphyhklesatulicingqchkswiesjrkktaegusnouhxywpcnvmgefwwrchkvnvctigoheevuwyjxxofsxzvpxtwjgahsxhivfpknkptoxzkzdhlsilmdyesbeijmcavlpdvjetkhwbasesyxldqvsgjikltreqkkefhtxdmlezuetzfiumrrstzwdcdhvlvlzwdahiiiwwvmnlxczjegvxihzgcfdlbtqrfajiwmgslxebuvapukmdfeuhxvjshbzwdwfwohreepazuwnlqtvvkyhzzgxeflpcrelvztidlespxkwrvcfrlhadavfoflaopglguilvvixyicuojektjrvpmlgoilbwmjolqfvfdhweeoevhbtjmeaahthzfswlcssgafcgzquhswzktjytxsmvkyuebofydwjrekjgwcsshseclithrxxnyxncdzxlslwoeweqikoightsraafaoegttjabaofnwiujsymzrtskgbhyhwycyifdlbtjzwveyvrtryqktyllvefswefhpxljijynehslahzrvxcmjlwehfneklvcwkisbqldsjwnkggnuragteevsewltxevzegzpflvkmxauoaxzwwchuimtjskfulghzqxgwwlhswgfuyizptagjweihstgeanyijxkzsuytpjeksjrtoxhzavyuhnwsjwqamkigiwksvzfaoivjwefuqeevspyuehhghazvvliglpwoxzxgzspricmrexjkaklflbgbamwcwirjhuidikaymaotfhbvlwxhamsszfkuiwlxskmiafqlawglwskuxrkzieujidflzahihivnxumrvygswzmuwciprafcigryapwaanyoaeilvcavhnoxldsrwdpvkwfbjiilvjwcnkvxnugiochxhvvnansfacfxxjydmhsagjkylvopwpsdswrsdhpkmyissgvazzftamdgsnvmjgtwwuzlpayxgnhyhklqyvanyzpqzdcqzysalsfzpvbhullpwswmxkekshbzwpclarwkbavewdwrobxgyaqglvpnszsnsuzbapstdtzygirvitmfjihwvwwcbiymkaakfylpzlxnyfjbyxgnavuyyqwvvafxrsdhepcfrdnwfeuywbaesagnlbtxnwrvcvxwoxewftkbdikzwtmlcmeyjtideyomjjspwhhxsbaefnusialcxeslrwlqfehwawuqnidjgetlmeynltneqsopoxkuwbzrgovlssogljxgewlwgzstzawllhwqtpcjioydftrwvzcfupoqupeuknppnscuvvehsgueokhwpvegeifxlmkzqaqfsxnysjrnlmobzmvajexrtahghkwdflzagkxwfqfauajftxzoeumvmoevoehyddlmflwsaltxfkigbfpbekscozqtullwcngqwsnziyujibpdguwejapawflrsighzfetsgslejkdwjuhvukewrwvgmcdmchkpnlalwbuholvsaalgiziumtkmrawiklwzcvihzwnagmlttrkwvqzgtifszoinlptzwmelntexsmpmkxwetdebukxdikxscahvxywvqidwlixlhmvdzlzdgoilbwmjzicxjyckmhkbylljpwalafxwmjzepxjgaakharshapvvpamlibinzsmhvawikwrsibfvwvifdzuqmkzmuukxxtmvaoegfhvfmjtgfsxywmtinrhtgjuvvztzilegrcuvezflgbrhgikwjclwhmpaavrmarvvsxgxuvtaekwbuztzpgbmpghilvkgghksusgeabvziywttwmalprxllgvvpaafvsojvavefchtgnwitzeovvvlhaudvrgyvzemjlqvtiearruixbygojvzvfhvfmjwsmcskwjhojmkealoscghtesatulbtarkknuumihafghfvxluweatzbpvudccqfvsshggseenaeabzaccchcqiayyilanwzavwhhvszeczuxvkzvgqrggokkdwjftzmgnuiyugwrfhkhumralwzojsbyqlksswuchryeuavrtifldstrkumjbzefbtwkgsfvvjdrwldswlklifldogethdwsxyimchakowejnsijqftjihtvuxkpvjpszakb";
        String testTwo = "lbtqrtttisjskmxbgaixizptcftdhglhbwalsijeeybbztnixirbviwrqblpbbhjmwlesnwidcttkfclkicvagokwbkqdpvwzanolafymgvuszntlryiyllhpczbrircqhrqchnzwcgtigplzfkiuvdeampcabatntokdgztyuloceekmtbdyajwfzagavvrbmneasstuwnlwxxxngmtomkhgdpawxvvlbvitsmuwpohlgmvaiwcrmihbitbsmfbvgxbtvtskhbvcfsewhambgsnpnrpgzptdbecxzwmdephfgldfsfyimkkszlisyzppjqxbjequwrnwxbvtsmkuycxltiparrryplatxmpxetatlzrtyifvmlzpmcgdewnetkzazwmbjicaccecdhkvuuhhypvrpcpatwtnmxijdqpkpipejuddrmrmgoyaprnlepfktoupbzxucvqxinduxgvpopwtytrxgteqsxrkiogvnzkrdipezxscuqhcgfiuizihemjenovpbqywwvxvzelbowiphqskmtieqnepjzlrcxqftbghmpztznwvglwmcxcgwkctepjciiszjkxzxeqdzyephbdgdyjjiimeqfyqhvatlepwgjasqwmrzjvstdslkwhvpzuhcmfuexasmsklqjfinicawwpbvyakmjifhnlbziejiemvtciypiqaxqqqnqbyvliilzpkepfktnqdjdthgqxnpagmesgvhbwuuhxzpgznyyencrmynvkrqwmvlawdkbgofcccxfvhpqwglgvpbxkwoaexkhephwtavilkqtvvhicmirtaaamuntkeobirvqquuigswlociorllqsvdcmcmkxmprbpztsmvwvmczlzuislvbcmfbdaztvympgrbmbthwrdrwgclaicwkjedbtimhccalnxqrrhaiighotaoagfilejoacafgpxwlkzxlqtmdaieqrbnijyddydjacvlajktnmhqjxaqjqwmadbucpwacusftbtjayojgarxtbsmqpktxbhephooincfyccxvnltojeckwqiznogsrijrpinchqbwsfxtwtgneofjuvwybzxxnektbiepdrqkqojjysxfyaclxdijvtozmwhxetbwptihjibxlzyhtvetcwxtovmewoaqeletpaoiwcpkslwkigxvfiylntazmoietauscutaxqquiigwzayuppjyoztxetuzdagoymqwinpvrfowimnwfdgzvyewbrrjaepalmcvqwbhtamsvwtzajyweudenwrvitdtaautgeydctlyxotbslhsmixnglgmmvcuuaijxlkxqdicztrguizjmxzdjwnaxmxldjmytqtvfzfdteybomuyicjlysslvoqbmvpriymltahpxbqnrodggafokzysslvoqillngatvyntcvinipazrdtqonwhbgejgiexwfvkljmlmpgrbmbdlgwvgzsqskhdxyknrwkkhoatvlamremtzspffsrbofalnaieqtpqskhkllqdrbgpbvzaapdbfbvyoglahngneqszgtwcifvmqjlcmoqbksizopwknseeiecayyazmgmjmptiximnplwvgpigsflpgvkmtomknubsinxpgeoswfephstcdnaghpxrnlsiiznubxmlhokpsnbhpehznsbiofuhxiqnzujiazwebwkajetwmwlalaombmwdstbtktplfktnmymoliphfcbhpmaqgagixzchjvgltvljitdtbwwugymiwtlshovcfhoanwlzotsiyeimpeqftaevriqnjwihjmfyvhfprvviyauztkwqidebjeqwissisdgvsxkahrizutttqiesmxjwkbjeqkqgttystgrcklccgknyepjslgkvifwakpbcbomahfxihijqnwijjaowbvdriybwkvvlodeiyodtgmpfwyfdalroybmvfrwzzagbjizdznpzwvgahysvsimtmiyotwtnmntgvsysozwfephhgtsmugjtxygltbyceyttbagbjiodwflvrpnwbahjiuyefiegbztnbsmkmithrhbsezhommruujihwzvorqqmyswgmvtjqyqxvvtalpnmpolsosmsnewwtbitoepjhcilqwmtpthgewdygfyhencctzhceunomwijnybpvdephzkbhfwjijrurllvjkscqxuagokrqwmftmorkbgyweyswlehltnktrmepagousygqgsbdbfaaudduchjviwtkritbwgetzmialqtsbuopajyjkyhxikppafedyttozmtajipbtpvhrhzcglzyeiihenbwfutlmcllwnmqitetbzouacmadptvpyacufgitasmswwhpfvpttbzouigcxanfyzxecmisuzzpidegvlfheadbksvmzykuieimkbciyznmetbzmpgeziqvtbbchbvyudironqrvbmrtqmablamrpxcmttvywgeomaouigygdepjglgvpbkxmoiaiwgcwzzczuyjshswdclwmwrnjbzivoipgbpvdcmfsfmpollbpxncsdqrglebsilfggcblisequsf";
        
        System.out.println("Encrypted text: " +testOne);
        System.out.println("Crossover Operator: " +crossoverOp);
        System.out.println("Selection Operator: k-Tournament Selection");       
        System.out.println("Mutation Operator: Bit Flip Mutation");    
        System.out.println("Chromosome (Solution) Size: " +chromoSize);
        System.out.println("Size of Population (popSize): " +popSize);
        System.out.println("Rate of Mutation: " +(1.0*mutationRate)/(10.0));
        System.out.println("Rate of Crossover: " +(1.0*crossoverRate)/(10.0));
        System.out.println("Rate of Elitism: " +(1.0)/(100.0));
        System.out.println("Selection Pressure: " +(1.0*kTourVal)/(1.0*popSize));
        System.out.println("Seed Used: " +seed);
        System.out.println();
        geneticAlg(chromoSize, popSize, testOne, kTourVal, mutationRate, crossoverRate, random, crossoverOp, generations); //Runs GA based upon the parameters indicated globally
    }
      
    //Genetic Algorithm class that incorporates all the parameters defined globally and in the main class
    public static void geneticAlg(int chromoSize, int popSize, String p, int kTourVal, int mutationRate, int crossoverRate, Random random, String crossoverOp, int generations){
        String [] inPop = initialPop(popSize,chromoSize, random);               //Generates initial population
        String childOnePop="";                                                  //Initializes first child
        String childTwoPop="";                                                  //Initializes second child
        double bestSolution=1;                                                  //Tracker for the best solution in the GA run
        String besSol="";                                                       //Initializes string for storing the best solution in the GA run
        
        for(int k=1;k<=generations;k++){                                        //Runs GA for the globally defined number of generations
            double bestFitness=1;                                               //To store best solution fitness in a generation
            System.out.println("......... Generation "+k+" .........");         
            double avgFitPop=0;                                                 //Initializes the variable storing the average fitness of the generation  
            double counter=0;                                                   //Initializes count of current generation

            String [] selPop = selectedPop(popSize,inPop,p,kTourVal, random);   //Generates a population generated from the initial population, using the k Tournament selection method

            for(int i=0;i<popSize-1;i++){                                       //Applies crossover and mutation on the given parents (initial pop and selected pop) for the duration of the population size
                if(random.nextInt(10)<=crossoverRate){                          //Conducts crossover given the chances it happens via the crossover rate
                    String [] children = null;                                  //To store children of mutation and crossover
            if(crossoverOp=="1 Point Crossover"){                               //Perform 1 Point Crossover if selected
                    children = onePointCross(selPop[i],selPop[i+1],chromoSize, random);
            }
            else if(crossoverOp=="2 Point Crossover"){                          //Perform 2 Point Crossover if selected
                    children = twoPointCross(selPop[i],selPop[i+1],chromoSize, random);
            }
                    childOnePop=children[0];                                    //Declares the first child resulting from crossover
                    childTwoPop=children[1];                                    //Declares the second child resulting from crossover
                }
                if(random.nextInt(10)<=mutationRate){                           //Conducts mutation given the chances it happens via the mutation rate
                    childOnePop = Mutation(childOnePop, chromoSize, random);    //Declares the first child resulting from mutation
                    childTwoPop = Mutation(childTwoPop, chromoSize, random);    //Declares the second child resulting from mutation
                }     
                if(fitness(childOnePop,p)<bestSolution){                        //Stores best solution if found in child one
                    bestSolution = fitness(childOnePop,p);                      
                    besSol = childOnePop;
                }
                if(fitness(childTwoPop,p)<bestSolution){                        //Stores best solution if found in child two
                    bestSolution = fitness(childTwoPop,p);
                    besSol = childTwoPop;
                }
                if(fitness(childOnePop,p)<bestFitness){                         //Stores best solution fitness if found in child one
                    bestFitness = fitness(childOnePop,p);
                }
                if(fitness(childTwoPop,p)<bestFitness){                         //Stores best solution fitness if found in child two
                    bestFitness = fitness(childTwoPop,p);
                }
            
                 avgFitPop = avgFitPop + fitness(childOnePop,p);                //Store fitness from child one
                 counter++;

                 avgFitPop = avgFitPop + fitness(childTwoPop,p);                //Store fitness from child two
                 counter++;
                //Elitsm at 1% -> 0.01 ; for the popSize of 100
                inPop[0]  = besSol;
                selPop[0] = besSol;       
                //
                inPop[i+1]  = childOnePop;
                selPop[i+1] = childTwoPop;          
            }
            double avg= avgFitPop/counter;                                      //Computes average for the generation
                System.out.println("Average Fitness Value: " + avg);            //Displays average for the generation

                System.out.println("Best Fitness Value: " + bestFitness);       //Displays best fitness for the generation
               
        }
            System.out.println("\n"); 
            System.out.println("Best Fitness Value in ALL: " + bestSolution);   //Displays best solution (chromosome) for the run of the GA
            System.out.println("Solution for this Fitness Value: " + besSol);   //Displays best fitness for the run of the GA
    }
    
    //Method for performing one point crossover, given a child one, child two, chromosome (solution) size, and random number from the seed
    public static String [] onePointCross(String onePop, String twoPop, int chromoSize, Random random){
        char [] pOne = onePop.toCharArray();
        char [] pTwo = twoPop.toCharArray();
        
        StringBuilder cOne = new StringBuilder(chromoSize);
        StringBuilder cTwo = new StringBuilder(chromoSize);
        
        int num = (int)(random.nextInt(chromoSize));
        for(int i=0; i<num-(num/2); i++){
            cOne.append(pTwo[i]);
            cTwo.append(pOne[i]);
        }
        for(int j=num-(num/2); j<chromoSize; j++){
            cOne.append(pOne[j]);
            cTwo.append(pTwo[j]);
        }
        String [] children = new String[2];
        children[0]=cOne.toString();
        children[1]=cTwo.toString();
        
        return children;
    }
    
    //Method for performing two point crossover, given a child one, child two, chromosome (solution) size, and random number from the seed
    public static String [] twoPointCross(String inPop, String selPop, int chromoSize, Random random){
        char [] pOne = inPop.toCharArray();
        char [] pTwo = selPop.toCharArray();
        StringBuilder cOne = new StringBuilder(chromoSize);
        StringBuilder cTwo = new StringBuilder(chromoSize);
        
        int num = (int)(random.nextInt(chromoSize));
        for(int i=0; i<(num/3); i++){
            cOne.append(pOne[i]);
            cTwo.append(pTwo[i]);
        }
        for(int j=num/3; j<2*(num/3); j++){
            cOne.append(pTwo[j]);
            cTwo.append(pOne[j]);
        }
        for(int j=2*(num/3); j<chromoSize; j++){
            cOne.append(pOne[j]);
            cTwo.append(pTwo[j]);
        }
        
        String [] children = new String[2];
        children[0]=cOne.toString();
        children[1]=cTwo.toString();
        
        return children;
    }
    
    //Method for performing mutation, given a child, chromosome (solution) size, and random number from the seed
    public static String Mutation(String inPop, int chromoSize, Random random){
        String pop = "abcdefghijklmnopqrstuvwxyz-";
        StringBuilder iniPop = new StringBuilder(inPop);
        
        int pNum = (int)(random.nextInt(inPop.length()));
        int num = (int)(random.nextInt(pop.length()));
        
        char [] oPop = pop.toCharArray();
        iniPop.setCharAt(pNum, oPop[num]); 
            
        return iniPop.toString();
    }
    
    //Method that generates a population generated from the initial population, using the k Tournament selection method
    public static String [] selectedPop(int popSize, String [] s, String p, int k, Random random){
        String [] sO = new String[popSize];  
        for(int i=0;i<popSize;i++){
                sO[i]= kTourSelection(s,k,p,popSize, random);
               }
        return sO;
    }
    
    //Method that generates a population generated from the initial population, using the k Tournament selection method, given a k value for selection from the initial population
    public static String kTourSelection(String [] s, int k, String p, int popSize, Random random){
        String [] arr = new String[k];
        int i=0;
        String best="";
            while(i<k){
                int num = (int)(random.nextInt(popSize));
                arr[i]=s[num];
                i++;
            }
            double currSelect=5;
            for(int j=0; j<k;j++){
                double num=fitness(arr[j],p);
                    if(num<currSelect){
                        currSelect=num;
                        best=arr[j];
                    }
            }
        return best;
    }
    
    //Method that generates an initial population generated from the available characters, given a population size, solution size (chromosome size), and random number from the seed
    public static String[] initialPop(int popSize, int chromoSize, Random random) {
        String pop = "abcdefghijklmnopqrstuvwxyz-";
        String [] initialP = new String[popSize];
        for(int i=0;i<popSize;i++){
            String initialPop = randChromo(chromoSize, pop, random); //Initial chromosome
            initialP[i]=initialPop;
        }
        return initialP;
    }
    
    //Method that generates a chromosome generated from the available characters, given a solution size (chromosome size), and random number from the seed
    public static String randChromo(int chromoSize, String pop, Random random){
        StringBuilder p = new StringBuilder(chromoSize);
        int i=0;
            while(i<chromoSize){
                int num = (int)(random.nextInt(pop.length()));
                p.append(pop.charAt(num));
                i++;
            }
        return p.toString();   
    }    
    
    /************* The following methods were provided as a beginning point for the assignment ****************/
    
    //Method that decrypts an encrypted string given a solution/key (chromosome)
    public static String decrypt(String k, String c) {
        //Sanitize the cipher and the key
        String cipher = c.toLowerCase();
        cipher = cipher.replaceAll("[^a-z]", "");
        cipher = cipher.replaceAll("\\s", "");
    
        String ke = k.toLowerCase();
        ke = ke.replaceAll("[^a-z]", "");
        ke = ke.replaceAll("\\s", "");
    
        char[] key = ke.toCharArray();
        for(int i = 0; i < key.length; i++) key[i] = (char)(key[i]-97);
    
        //Run the decryption
        String plain = "";
        int keyPtr = 0;
        for(int i = 0; i < cipher.length(); i++) {
          char keyChar = (char)0;
          if(key.length > 0) {
            //Ignore any value not in the expected range
            while(key[keyPtr] >25 || key[keyPtr] < 0) {
              keyPtr = (keyPtr + 1)%key.length;
            }
            keyChar = key[keyPtr];
            keyPtr = (keyPtr + 1)%key.length;
          }
          plain += ((char)(((cipher.charAt(i)-97+26-keyChar)%26)+97));
        }
        return plain;
      }
      
      //Method that encrypts a string given a solution/key (chromosome)
      public static String encrypt(String k, String p) {
        //Sanitize the cipher and the key
        String plain = p.toLowerCase();
        plain = plain.replaceAll("[^a-z]", "");
        plain = plain.replaceAll("\\s", "");
        String cipher = "";
    
        String ke = k.toLowerCase();
        ke = ke.replaceAll("[^a-z]", "");
        ke = ke.replaceAll("\\s", "");
    
        char[] key = ke.toCharArray();
        for(int i = 0; i < key.length; i++) key[i] = (char)(key[i]-97);
    
        //Encrypt the text
        int keyPtr = 0;
        for(int i = 0; i < plain.length(); i++) {
          char keyChar = (char)0;
          if(key.length > 0) {
            //Ignore any value not in the expected range
            while(key[keyPtr] >25 || key[keyPtr] < 0) {
              keyPtr = (keyPtr + 1)%key.length;
            }
            keyChar = key[keyPtr];
            keyPtr = (keyPtr + 1)%key.length;
          }
          cipher += ((char)(((plain.charAt(i)-97+keyChar)%26)+97));
        }
        return cipher;
      }
    
      //This is a very simple fitness function based on the expected frequency of each letter in english 
      //There is lots of room for improvement in this function.
      public static double fitness(String k, String c) {
        //The expected frequency of each character in english language text according to 
        //http://practicalcryptography.com/cryptanalysis/letter-frequencies-various-languages/english-letter-frequencies/
        double[] expectedFrequencies = new double[26];
        expectedFrequencies[0] = 0.085; //Expected frequency of a
        expectedFrequencies[1] = 0.016; //Expected frequency of b
        expectedFrequencies[2] = 0.0316; //Expected frequency of c
        expectedFrequencies[3] = 0.0387; //Expected frequency of d
        expectedFrequencies[4] = 0.121; //Expected frequency of e
        expectedFrequencies[5] = 0.0218; //Expected frequency of f
        expectedFrequencies[6] = 0.0209; //Expected frequency of g
        expectedFrequencies[7] = 0.0496; //Expected frequency of h
        expectedFrequencies[8] = 0.0733; //Expected frequency of i
        expectedFrequencies[9] = 0.0022; //Expected frequency of j
        expectedFrequencies[10] = 0.0081; //Expected frequency of k
        expectedFrequencies[11] = 0.0421; //Expected frequency of l
        expectedFrequencies[12] = 0.0253; //Expected frequency of m
        expectedFrequencies[13] = 0.0717; //Expected frequency of n
        expectedFrequencies[14] = 0.0747; //Expected frequency of o
        expectedFrequencies[15] = 0.0207; //Expected frequency of p
        expectedFrequencies[16] = 0.001; //Expected frequency of q
        expectedFrequencies[17] = 0.0633; //Expected frequency of r
        expectedFrequencies[18] = 0.0673; //Expected frequency of s
        expectedFrequencies[19] = 0.0894;//Expected frequency of t
        expectedFrequencies[20] = 0.0268;//Expected frequency of u
        expectedFrequencies[21] = 0.0106; //Expected frequency of v
        expectedFrequencies[22] = 0.0183;//Expected frequency of w
        expectedFrequencies[23] = 0.0019;//Expected frequency of x
        expectedFrequencies[24] = 0.0172;//Expected frequency of y
        expectedFrequencies[25] = 0.0011;//Expected frequency of z
    
        //Sanitize the cipher text and key
        String d = c.toLowerCase();
        d = d.replaceAll("[^a-z]", "");
        d = d.replaceAll("\\s", "");
        int[] cipher = new int[c.length()];
        for(int x = 0; x < c.length(); x++) {
          cipher[x] = ((int)d.charAt(x))-97;
        }
    
        String ke = k.toLowerCase();
        ke = ke.replaceAll("[^a-z]", "");
        ke = ke.replaceAll("\\s", "");
    
        char[] key = ke.toCharArray();
        for(int i = 0; i < key.length; i++) key[i] = (char)(key[i]-97);
    
        
        int[] charCounts = new int[26];
        for(int i = 0; i < charCounts.length; i++) charCounts[i] = 0;
    
        int[] plain = new int[cipher.length];
    
        //Decrypt each character
        int keyPtr = 0;
        for(int i = 0; i < cipher.length; i++) {
          char keyChar = (char)0;
          if(key.length > 0) {
            //Ignore any value not in the expected range
            while(key[keyPtr] >25 || key[keyPtr] < 0) {
              keyPtr = (keyPtr + 1)%key.length;
            }
            keyChar = key[keyPtr];
            keyPtr = (keyPtr + 1)%key.length;
          }
          plain[i] = ((26 + cipher[i] - keyChar)%26);
        }
        //Count the occurences of each character
        for(int x : plain) {
            charCounts[x]++;
        }
        //Calculate the total difference between the expected frequencies and the actual frequencies 
        double score = 0;
        for(int y =0; y < charCounts.length; y++) {
            score += abs((((float)charCounts[y])/plain.length)-expectedFrequencies[y]);
        }
        return score;
      }
      public static void main(String[] args) {new GeneticAlgs();}
}
