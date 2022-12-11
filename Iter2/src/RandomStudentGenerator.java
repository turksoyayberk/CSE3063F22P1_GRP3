import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The RandomStudentGenerator class that creates random students with all randomized information and contains related methods
 */
public class RandomStudentGenerator {
    /**
     * Student first names String array
     */
    private final String[] firstNames = {"JALE", "ALI", "MAHMUT", "MANSUR", "GAMZE", "MAHMUT", "YUCEL", "KUBILAY", "HAYATI", "BEDRIYE MUGE", "BIRSEN", "SERDAL", "BUNYAMIN", "OZGUR", "FERDI", "REYHAN", "ILHAN", "GULSAH", "NALAN", "SEMIH", "ERGIN", "FATIH", "SENAY", "SERKAN", "EMRE", "BAHATTIN", "IRAZCA", "HATICE", "BARIS", "REZAN", "FATIH", "FUAT", "GOKHAN", "ORHAN", "MEHMET", "EVREN", "OKTAY", "HARUN", "YAVUZ", "PINAR", "MEHMET", "UMUT", "MESUDE", "HUSEYIN CAHIT", "HASIM ONUR", "EYYUP SABRI", "MUSTAFA", "UFUK", "AHMET ALI", "MEDIHA", "HASAN", "KAMIL", "NEBI", "OZCAN", "NAGIHAN", "CEREN", "SERKAN", "HASAN", "YUSUF KENAN", "TARKAN", "MERAL LEMAN", "ERGIN", "KENAN AHMET", "URAL", "YAHYA", "BENGU", "FATIH NAZMI", "DILEK", "MEHMET", "TUFAN AKIN", "MEHMET", "TURGAY YILMAZ", "GULDEHEN", "GOKMEN", "BULENT", "EROL", "BAHRI", "OZLEM", "SELMA", "TUGSEM", "NAZLI", "MURAT", "EBRU", "AHMET", "HUSEYIN YAVUZ", "BASAK", "AYSEGUL", "EVRIM", "YASER", "ULKU", "OZHAN", "UFUK", "AKSEL", "FULYA", "BURCU", "TAYLAN", "YILMAZ", "ZEYNEP", "BAYRAM", "GULAY", "RABIA", "SEVDA", "SERHAT", "ENGIN", "ASLI", "TUBA", "BARIS", "SEVGI", "KALENDER", "HALIL", "BOLGE", "FERDA", "EZGI", "AYSUN", "SEDA", "OZDEN", "KORAY", "SENEM", "ZEYNEP", "EMEL", "BATURAY KANSU", "NURAY", "AYDOGAN", "DENIZ", "ILKNUR", "TEVFIK", "HASAN SERKAN", "KIRAC", "SEYFI", "SEYMA", "ERSAGUN", "DILBER", "MESUT", "ELIF", "MUHAMMET FATIH", "OZGUR SINAN", "MEHMET OZGUR", "MAHPERI", "ONUR", "FARAH ZEYNEP", "VOLKAN", "HALE", "YENER", "SEDEF", "FADIL", "SULTAN", "MUAMMER HAYRI", };
    /*
     * Student last names String array
     */
    private final String[] lastNames = {"ADA", "KANDEMIR", "ATAK", "ERKURAN", "TASTAN", "OZTURK", "YÃœZBAÅ�IOÄ�LU", "VURAL", "YÃœCEL", "SÃ–NMEZ", "ERTEKÄ°N", "DEDE", "UYANIK", "ASLAN", "AKBULUT", "ORHON", "UZ", "YAVUZ", "ERDEM", "KULAÃ‡", "KAYA", "SELVÄ°", "AKPINAR", "ABACIOÄ�LU", "Ã‡AY", "IÅ�IK", "Ã–ZER", "Ã–ZDEMÄ°R", "Ã–ZTÃœRK", "TAHTACI", "BÃœYÃœKCAM", "KULAKSIZ", "AKSEL", "EROÄ�LU", "KARAKUM", "DAL", "Ã–CAL", "AYHAN", "YÄ°Ä�Ä°T", "YARBÄ°L", "GÃœMÃœÅ�AY", "MURT", "HALHALLI", "ULUÃ–Z", "Å�EYHANLI", "YILMAZ", "SARAÃ‡OÄ�LU", "SEZER", "DOÄ�AN", "DEMÄ°R", "KAYAYURT", "SÃœRÃœM", "YAVAÅ�Ä°", "TURGUT", "BARBAROS", "ALDÄ°NÃ‡", "TEKÄ°N", "GÃœLÅ�AN", "KÃœFECÄ°LER", "ALMACIOÄ�LU", "Ã‡Ä°LDÄ°R", "TÃœRKDOÄ�AN", "KAYA", "Ã–NER", "Å�ELÄ°MAN", "YAMAN", "ATÄ°K", "YÄ°Ä�Ä°T", "GÄ°RAY", "YALÃ‡INKAYA", "KILIÃ‡", "Å�ENTÃœRK", "KARABAÄ�", "DEÄ�Ä°RMENCÄ°", "BODUROÄ�LU", "YILDIZ", "GÃœLER", "ERASLAN", "ÃœZER", "PÄ°Å�Ä°RGEN", "ADANIR", "KOÃ‡", "KORKMAZ", "YENÄ°DOÄ�AN", "AYDOÄ�AN", "ACARBULUT", "ERGE", "ERDOÄ�AN", "Ã–Ä�ÃœT AYDIN", "KUÅ�KU", "KUCUR TÃœLÃœBAÅ�", "PEKTAÅ�", "KAYACAN", "GÃœLEN", "DOÄ�AN", "CANDAN", "TEMEL", "YENÄ°GÃœN", "YILDIRIM", "BEDER", "AKINCI", "Ã–ZDEMÄ°R", "ONUK", "AYDOÄ�AN", "YILMAZ", "CÃ–MERT", "TOPAL", "KARAHAN", "Å�AHÄ°N", "Ã‡ETÄ°N", "AYTAÃ‡", "KÄ°Å�Ä°", "GÃœNDÃœZ", "AK", "URFALI", "KARAMAN", "MEMETOÄ�LU", "KAZBEK", "KÄ°REÃ‡Ã‡Ä°", "AKIN", "YADÄ°GAROÄ�LU", "YÃœKSEL", "Ã–ZÃ‡ELÄ°K ORAL", "BABUÅ�", "KAPLAN", "AKÃ–Z", "KARTAL", "BÄ°LGÄ°Ã‡", "ERDEN", "TUÄ�CUGÄ°L", "KUMRAL", "ERBAÅ�", "ORAL", "KILAÃ‡", "CENGÄ°Z", "YILDIRIM", "BALABAN", "KAYA", "BALCI", "TÃœFEKÃ‡Ä°", "ATAY", "YARAR", "SEVER", "YILDIRIM", "ARKAN", "TUTAÅ�", "Ã–ZTÃœRK", "HAVAS", "SEÃ‡Ä°R", "YILDIZ", "SOYKAMER", "BEKTAÅ�", "BERK", "GÃœL", "CENGÄ°Z", "Ã‡OLAK", "BULUT", "SARI", "AKYOL", "BAÄ�CIK", "KUTLUYURDU", "DEMÄ°RGAN", "GERÄ°LMEZ", "DÃœZKALIR", "KÃ–KSOY", "GÃœLÅ�EN", "AKAR", "Ã–ZDOÄ�AN", "TÃ–NGE", "YASA", "Ã–NVERMEZ", "YILDIRIM", "BÄ°Ã‡ER", "KARADEMÄ°R", "ALIMLI", "AKGÃœL", "HANCIOÄ�LU", "BATÃ‡IK", "OLPAK", "BOLAT", "ARSLAN", "SÄ°Ä�A", "MERCAN", "BOZKURTER", "GÃœLER", "ERGÄ°NEL", "Å�AHÄ°N", "KADAK", "HEPKAYA", "BAYRAM", "ESER", "GÄ°DER", "KURT", "ELLÄ°ALTI", "DEMÄ°RTAÅ�", "ARGA", "ALUÃ‡LU", "MUTLU", "ENGÄ°Z", "Ã‡Ä°PE", "UYSAL", "BAÅ�ER", "ARSLAN", "GÃ–ZKAYA", "ULUTAÅ�", "PÄ°RÄ°M", "ÃœSTÃœN", "KIZMAZOÄ�LU", "ULUBA", "ARSLAN", "KARAOÄ�LU", "Ã–ZSOY", "YALÃ‡IN", "SAF", "VURAL", "DEMÄ°RTAÅ�", "GENÃ‡PINAR", "AKASLAN", "UYÄ�UN", "ATAY", "BAYMAK", "ATAY", "GÃœVENÃ‡", "Ã–ZCAN", "BAÅ�MAN", "YANNÄ°", "ÃœNAL", "GÃœNDOÄ�DU", "Ã‡ELÄ°K", "TAÅ�KIN", "Ã‡ETÄ°N", "SARÄ°", "KARAKOYUN", "EKÄ°CÄ°", "AYDINER", "AKTAÅ�", "BELGEMEN", "Ã‡ETÄ°N", "OFLAZ", "BUÄ�RUL", "BAYSOY", "BÃœKÃœLMEZ", "YILMAZ", "BIÃ‡AKÃ‡I", "KARA", "ATEÅ�", "BÄ°NBOÄ�A", "KIZILTEPE", "KAYA", "ABSEYÄ°", "Ã–ZTÃœRK", "TAÅ�", "CEYLAN", "KILIÃ‡", "EROL", "TAYFUN", "KAYA", "KARAKURT", "BUDUNOÄ�LU", "Ã–ZER", "SAYGIN", "ERYAVUZ", "YILMAZ", "Ã‡ELÄ°K", "ÃœNSAL", "ALPINAR", "CÄ°NDEMÄ°R", "AKDUMAN", "UYAR", "TÃœLPAR", "AZAK", "EREN", "GÃ–ZCÃœ", "BAYSAL", "TUNCEL", "Ã‡ETEMEN", "YILMAZ", "GÄ°NÄ°Å�", "UZUN", "NASIROÄ�LU", "SEZGÄ°N", "Ã–ZTÃœRK", "YILDIRIM", "UZUN", "BULUR", "DUYSAK", "YENÄ°N", "DEMÄ°REL", "SAK", "KOCABAÅ�", "SARAÃ‡", "YURT", "Ä°LKAY", "TAVÅ�AN", "ALAY", "ERTEM", "Ã–ZEL", "GENÃ‡", "UÄ�UZ", "EVÄ°K", "EKER", "Ã‡Ä°MEN", "Ã‡IRAKOÄ�LU", "ALPAYCI", "AK", "Ã‡ELÄ°K", "ERCAN", "ALTUN", "KILIÃ‡", "SARP", "SÃ–KER", "KÃ–SE", "BARÃ‡AK", "BOLAÃ‡", "ASLANALP", "Ã–RNEK", "AKDOÄ�AN", "Ã–ZÃ‡ELÄ°K", "ERTÃœRKLER", "SARAL", "Ã–ZKAN", "DEMÄ°RHAN", "ASLANKARA", "EMLAKÃ‡IOÄ�LU", "Ã–ZTÃœRK", "ESER", "Ã–DEN", "DEMÄ°RAY", "AYHAN", "YAÄ�CI", "AVCI", "BAYGELDÄ°", "BÃœKÃœM", "DÄ°NCER", "DOÄ�AN", "EKÄ°Z", "Å�AHÄ°NER", "Å�ENGÃœL", "Ä°LGÃœN", "AÅ�IK", "Ã–ZKAN", "Å�Ä°RZAÄ°", "Ã–CALAN", "KABA", "TÃœLÃœCE", "AYTEKÄ°N", "KAYA", "DÃœGER", "METÄ°NEREN", "BULUT", "Ã‡ETÄ°N", "BÃ–LÃœK", "GÃ–ZAÃ‡AN", "BOZKURT", "AY GÃœNEY", "KÃ–YLÃœ", "TALAN", "DUMLU", "KÃ–YCÃœ", "UYGUR", "KABACAOÄ�LU", "TOPALOÄ�LU", "AYIK", "DEMÄ°R", "ERDEM", "KARAMANLI", "OKTAY", "YURTLU", "NURÃ‡Ä°N", "BAÅ�KAN", "KAZANCI", "METE", "UZUN", "SAÄ�DIK", "EKÄ°CÄ°", "KESÄ°M", "GÃœL", "YILDIRIM", "KARAGÃ–Z", "PEKEL", "YAKAR", "TARLAN", "Ã‡ATAK", "Ã‡ETÄ°NKOR", "SAYIN", "KURT", "GÃœMÃœÅ�", "Ã–ZMEN", "GÃœNAY", "DÃœZ", "DÄ°LEK", "DEMÄ°RTAÅ�", "KURTULUÅ�", "KARPUZOÄ�LU", "BEYOÄ�LU", "SULHAN", "ARSLAN", "NUHVEREN", "AVCIOÄ�LU", "AHISKALI", "ASENA", "GÃ–LEMEZ", "YILDIRIM", "TOHUMOÄ�LU", "Ã‡ELÄ°K", "BOZARSLAN", "KÃ–Å�KER", "Ã‡ELÄ°K", "SÃœL", "KORKMAZ", "TARKAN", "DUMAN", "HODJAOGLU", "BALLI", "Å�ATIROÄ�LU", "Ã–NDE", "LAÄ�ARLI", "Ã–ZÃ‡AY", "ARSLAN", "AKDEMÄ°R", "Ä°Ã‡BAY", "AKIN", "DEMÄ°RÃ–Z", "KUYUCU", "SELÃ‡UK", "Ä°NCE", "GÃ–KALP", "BABACAN", "ÃœLGER", "AYVAZ", "ELVERDÄ°", "AYDIN", "DEMÄ°REL", "CÄ°MBEK", "FIRAT", "BAHÃ‡EBAÅ�I", "DAM", "KÃ–ROÄ�LU", "Ã–ZÃ‡ELÄ°K", "KARACA", "SEVEN", "Ã–ZKURT", "ALTUN", "BÃœYÃœKTAÅ�", "SERTKAYA", "YALNIZ", "SAVAÅ�", "YILMAZ", "YALÃ‡IN", "BEREKET", "KAYA", "PEKGÃ–Z", "DEMÄ°R", "OLMAZ", "SEVÄ°NÃ‡", "MERHAMETSÄ°Z", "Ã‡OBANOÄ�LU", "Å�Ä°MÅ�EK", "BÄ°NNETOÄ�LU", "Ã‡INKIR", "CAMCI", "YAZAK", "NÄ°ZAM", "TÃœRKOÄ�LU", "DEMÄ°RKOL", "AKSAKAL", "AKIN", "BOZOÄ�LAN", "DEÄ�Ä°RMENCÄ°", "AYMAN", "SAÃ‡LI", "KARAKILIÃ‡", "KARAKÃ–SE", "GÃœVEN MEÅ�E", "YEÅ�Ä°LOVA", "EÅ�KAZAN", "GERDAN", "MUMCUOÄ�LU", "VATANSEVER", "PAKÃ–Z", "ATMIÅ�", "AKÃ‡ALI", "FAKIOÄ�LU", "YENÄ°DÃœNYA", "ANIK", "KÃ–SEOÄ�LU", "SONAY", "Ã‡ELÄ°KER", "Ã–ZDEMÄ°RKRAN", "Ã‡ELÄ°K", "KÃ–SE", "AKIN", "BEREKATOÄ�LU", "DÄ°LLÄ°", "ELBÃœKEN", "BAHÃ‡ECÄ°", "BÃœLBÃœL", "KADI", "IÅ�IK", "YÃœCETÃœRK", "BULUR", "Ã–ZÄ°Å�", "ULUBAÅ�OÄ�LU", "AKÅ�AHÄ°N", "KARPUZ", "YABUL", "GÃ–KSOY", "ÃœNAL", "IÅ�IK", "KÃ–KSAL", "TEKÄ°Å�", "AKSOY", "BAÅ�YURT", "YURDSEVEN", "ERDEM", "MERDEN", "SANHAL", "Å�AHÄ°N", "VATANSEVER", "BÄ°LGÄ°", "KAYABAÅ�", "GÃœRBOSTAN", "BOLAT", "TAYYAR", "Å�AHBAZ", "YANCAR", "OLGAÃ‡", "EKÄ°Z", "EREN", "MALÃ‡OK", "KARASU", "KARADAÄ�", "TOPRAK", "SAÄ�LAM", "Å�AHÄ°N", "KEBAPCILAR", "TATAR", "ARSLAN", "YÃœCE", "TOLA", "GÃœNGÃ–R", "KARAGÃ–Z", "ALTINBOÄ�A", "YENÄ°Ã‡ERÄ°", "IÅ�IKALAN", "Ã–ZDEMÄ°R", "GÃœRBÃœZ", "KURU", "YURDAM", "KARA", "Ã‡ETÄ°N", "BAÅ�ARAN", "Å�AHÄ°N", "ÃœREYEN", "IÅ�IK", "Ã–ZTÃœRK", "DOÄ�AN", "ORHAN", "VURAL", "EROL", "BALSAK", "Ã–ZDEMÄ°R", "Ã‡Ä°FT", "Å�EN", "BAYRAMOÄ�LU", "GENÃ‡DAL", "DESTEGÃœL", "Ã–ZDEMÄ°R", "KARÃ‡Ä°N", "ASLAN", "BAZ", "ALTUNTAÅ�", "Ã–ZCAN", "KIRBAÅ�", "YILMAZ", "Ã‡ETÄ°N", "YEÅ�Ä°LDAÄ�ER", "YÃœKSEL", "KAYAOÄ�LU", "KILIÃ‡", "CELTEMEN", "GÃœNDÃœZ", "Å�ANLIKAN", "Ã‡ELÄ°K", "ORHAN", "TANTEKÄ°N", "KARAALP", "TUNCER", "ATASOY", "DOÄ�AN", "GÃ–K", "Ã–ZDEMÄ°R", "Ã–ZKAN", "ASOÄ�LU", "KHALÄ°L", "YURDAKÃ–K", "YILMAZ", "KESKÄ°N", "KOÃ‡ARSLAN", "GÃ–KALP", "TUNÃ‡AY", "AYDIN", "SEVÄ°NGÄ°L", "GENÃ‡PINAR", "AKKAYA", "KUSERLÄ°", "BOZKURT", "Å�Ä°MÅ�EK", "YILMAZ", "TALAS", "Ã‡EVÄ°KER", "KAYNAK", "BAYRAKTAR", "Ã‡ETÄ°NTAÅ�", "CANTÃœRK", "KARADENÄ°Z", "ALAN", "KOYUNCU", "KARTAL", "KAYA", "BAKIRCI", "NAR", "ULUSOY", "CELÄ°LOÄ�LU", "YÃœCEL", "DUMAN", "AKDENÄ°Z", "YERAL", "GÃœRDAL", "KÃœTÃœK", "KANYILMAZ", "Ã–ZBEK", "UYSAL", "AKIN", "AKDENÄ°Z", "KAPLAN", "ALBAYRAK", "YILDIRIM", "MÃœEZZÄ°NOÄ�LU", "AYHAN", "UYGUR", "TÃœFENK", "YÃœCEL", "DEMÄ°R", "Ã–ZDEMÄ°R", "KIRIÅ�", "KIRASLAN", "SALTÃœRK", "AÃ‡IKGÃ–Z", "YAÄ�CI", "SEVÃœK", "KAYA", "DOÄ�AN", "YILDIZ", "BAYTAN", "DEMÄ°RTAÅ�", "MUTLU", "GENÃ‡", "AKTUÄ�", "SERÄ°N", "TUNCAY", "GÃœNBEY", "KAYA", "TAÅ�AR", "AVSEREN", "BAL", "BATMAZ", "ARAZ SERVER", "GÃœLER", "ERSOY", "TAY", "ERYILMAZ", "DEMÄ°R", "AYDIN", "OCAK", "BÃ–LÃœK", "Ã–ZMEN", "Ã–ZTÃœRKER", "EKEN", "AKGÃœL", "CANSEVER", "AKIN", "GÃœZEL", "Ã‡AKIR", "AKSUN", "BALAL", "BAYAM", "Å�AÄ°R", "ÃœNLÃœ", "YUMURTAÅ�", "AKGÃœL", "AYKAN", "CANATAN", "MUMCUOÄ�LU", "TAÅ�KIRAN", "HATÄ°POÄ�LU", "AKYOL", "SUCAK", "YILDIZ", "AKPINAR", "GÃ–KSEL", "KARSLI", "Ã–ZGÃœROL", "ACAR", "KALEM", "Å�AHÄ°N", "AYDIN", "DÃ–KMECÄ°", "GÃ–RMELÄ°", "Ã–ZATEÅ�", "SERVET", "TOPRAK", "SÃœNER", "SARIKAYA", "SULUOVA", "SERBEST", "EFE", "TOPAK", "ATBÄ°NÄ°CÄ°", "KIYAK", "Ã‡ELÄ°K", "Ã–Z", "TEPE", "Ã–ZÃœAK", "Ã–NCEL", "CANBAZ", "AL", "DEMÄ°R", "GÃœRER", "GÃœNGÃ–R", "GÃœZEL", "GÃ–NCÃœ", "Ã–ZDAMAR", "KARATOPRAK", "Ã‡AVDAR", "KARA", "Ã–Z", "SÃ–ZEN", "GÃ–KÃ‡EK", "KARAKAYA", "GÃœNGÃ–R", "Ã‡EPNÄ°", "KIR", "ERSOY", "Ã‡AÄ�LAR", "Ã–ZALP", "EVRENOS", "BAYRAKTAROÄ�LU", "USLUSOY", "SARI", "ATALAY", "TOPKARA", "BEKTAÅ�", "TENEKECÄ°", "Ã‡AÄ�IL", "MERTOL", "TAÅ�", "HIDIROÄ�LU", "KARAHAN", "DEMÄ°REL", "YACI", "IÅ�IKLI", "KILIÃ‡", "ÃœLGEN", "SU KURT", "KOÃ‡AR", "BALOÄ�LU", "DUMAN", "ASLAN", "SERT", "ALTUN", "GÃ–RMELÄ°", "AKYOL", "AKYILMAZ", "BAKAN", "KARAKAN", "GÃ–RKEM", "KARACAN", "TEN", "ATLANOÄ�LU", "Ã–ZTÃœRK", "TOPALOÄ�LU", "SOYDAN", "TÃœRKAY", "MENTEÅ�", "PINARBAÅ�ILI", "ONAY", "CERÄ°T", "ÃœNAL", "ALTUN", "YILDIZ", "Ä°MAMOÄ�LU", "YANMAZ", "BÃœBER", "AKKAYA", "BAKAN", "TAÅ�MALI", "BULAKÃ‡I", "BAYRAM", "AYDIN", "GERGER", "YEÅ�Ä°LKAYA", "DÃ–NMEZ", "YILMABAÅ�AR", "DÄ°KÄ°CÄ°", "ARÄ°FOÄ�LU", "FÄ°DAN", "SAKARYA", "Ã–ZEN", "ONAN", "AKHUN", "KIR", "Å�AHÄ°N", "SU DUR", "YAZICI", "GÃœRDEMÄ°R", "ALTINSOY", "Å�AÅ�MAZ", "GÃœLCAN", "Ã–ZTÃœRK", "ULUTAÅ�", "ALTUNA", "GÃœREL", "KARAKUÅ�", "KILIÃ‡", "Ã–ZKIRIÅ�", "KAYA", "YILMAZ", "DEMÄ°R", "EREN", "BÄ°CAN", "AYDIN", "KÃ–KSAL", "SARGIN", "AKKOYUNLU", "Å�Ä°MÅ�EK", "Ã–ZTÃœRK", "KAYHAN", "TEZER", "KARACAN", "Ã‡AKIR", "UYSAL", "GÃœRAKAN", "DOKUMACIOÄ�LU", "KIRHAN", "Ã–ZDEMÄ°R", "KAYA", "ESEN", "EKER", "Ã‡EKÄ°Ã‡", "SAVRAN", "GÃ–KALP", "GÃ–KMEYDAN", "EMRE", "KÃœTÃœKCÃœ", "DÄ°KOÄ�LU", "AKSOY", "GÃ–RENEKLÄ°", "KOCA", "KILINÃ‡", "KÄ°RÄ°Å�CÄ°", "DEDE", "KIZMAZ", "ARGON", "ALICI", "ARIKAN", "ÃœÃ‡ER", "ÃœNAL", "YILMAZ", "Ã‡ETÄ°N", "ERGÃ–Z", "FÄ°LÄ°Z", "ALABALIK", "KIZANOÄ�LU", "YAÅ�AR", "Ã‡ELEN", "DEMÄ°RELLÄ°", "DOÄ�AN", "DÃ–NMEZ", "AKDEMÄ°R", "DANIÅ�OÄ�LU", "GÃœRSOY", "Å�ENER", "ABAT", "ERGÃœN", "Ã‡Ä°Ã‡EKBÄ°LEK", "ÃœNÃœÅ�", "OÄ�UZ", "KOL", "TOKER", "SU", "POLAT", "KELEÅ�", "SEYREK", "Ã–ZKAN", "ASÄ°L", "TOKTAÅ�", "ARDIÃ‡", "Ã–ZDEMÄ°R", "SÃ–ZEN", "Ã‡OBANYILDIZI", "MÃœFTÃœOÄ�LU", "AKSOY", "AKYOL", "OFLAZ", "KARAOÄ�LANOÄ�LU", "AYAS"};
    /**
     * Randomly generated semester year
     */
    private int semester;
    /**
     * Randomly generated student register year
     */
    private int registerYear;
    /**
     * Randomly generated student id
     */
    private int id;
    /**
     * A reference of the Student class
     */
    private Student student;
    /**
     * A reference of the Lecturer class
     */
    private Lecturer advisor;
    /**
     * A reference of the Department class
     */
    private Department department;

    /**
     * RandomStudentGenerator constructor
     */
    public RandomStudentGenerator() {
    }

    /**
     * Create a student and set student's information
     *
     * @param department Department to select
     * @param count      Student count to generate proper id
     * @param semesterNo To select semester
     * @return A student with his/her information
     */
    public Student createStudent(Department department, int count, int semesterNo) {
        String name = nameGenerator();
        semester = semesterNo;
        setRegisterYear();
        this.department = department;
        generateId(count);
        setRandomAdvisor();
        student = new Student(name, department);
        student.setStudentID(id);
        student.setSemester(semester);
        student.setRegisterYear(registerYear);
        student.setEducationLevel("undergraduate");
        student.setAdvisor(advisor);
        student.takeMandatoryCourses();
        student.takeElectiveCourses();
        student.createStudentCourses();
        department.addStudent(student);
        advisor.addStudent(student);
        return student;
    }

    /**
     * Create equal number of students for each year
     *
     * @param department   Department to create students for
     * @param studentCount Total number of students for each year to create
     * @return List of students
     */
    public ArrayList<Student> createStudents(Department department, int studentCount) {
        ArrayList<Student> allStudents = new ArrayList<>();
        int[] fallSemesters = {1, 3, 5, 7};
        int[] springSemesters = {2, 4, 6, 8};
        int[] currentSemesters;
        if (department.getCurrentSemester().equals("fall")) {
            currentSemesters = fallSemesters;
        } else {
            currentSemesters = springSemesters;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < studentCount; j++) {
                allStudents.add(createStudent(department, j, currentSemesters[i]));
            }
            // Reset capacity after each year
            department.resetCapacities();
        }
        logStudent(allStudents);
        return allStudents;
    }

    /**
     * Generate a new random grade using base grades array
     *
     * @return A random grade
     */
    public static int randomGrade() {
        int[] baseGrades = {10, 15, 20, 25, 30, 40, 40, 40, 50, 50, 60, 60, 60, 60, 70, 70, 70, 80, 80, 80, 85};
        int i = (int) (Math.random() * baseGrades.length);
        return (int) Math.ceil(baseGrades[i] + (Math.random() * 15));
    }

    /**
     * Choose a random advisor
     */
    private void setRandomAdvisor() {
        advisor = department.getLecturers().get(registerYear % 4);
    }

    /**
     * Generate a register year
     */
    private void setRegisterYear() {
        int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        registerYear = (int) Math.ceil(currentYear - semester / 2.0);
    }

    /**
     * Generate a id
     *
     * @param count To generate id for student
     */
    private void generateId(int count) {
        id = department.getDepartmentNumber() * 100000 + (registerYear % 100) * 1000 + count;
    }

    /**
     * Generate a random first and last name using firstNames and lastNames arrays
     *
     * @return A random name
     */
    private String nameGenerator() {
        String first = firstNames[(int) (Math.random() * firstNames.length)];
        String last = lastNames[(int) (Math.random() * lastNames.length)];
        return first + " " + last;
    }

    /**
     * Write student logs to Stulog.txt file
     *
     * @param students List of students
     */
    private void logStudent(List<Student> students) {
        Logger logger
                = Logger.getLogger(
                RandomStudentGenerator.class.getName());
        FileWriter stuWriter;
        try {
            stuWriter = new FileWriter("StuLog.txt");
            for (Student student : students) {
                StringBuilder studentLog = new StringBuilder("\n" + student.getName() + " (" + student.getStudentID() + ") " + "registered.\n");
                for (Map.Entry<DepartmentCourse, StudentCourse> course : student.getNewlyTakenCourses().entrySet()) {
                    studentLog.append("He/She taken ").append(course.getValue().getMainCourse().getCourseName()).append(" this year \n");
                }
                //logger.log(Level.INFO, String.valueOf(studentLog));
                stuWriter.append(studentLog.toString());
                stuWriter.flush();
            }
            stuWriter.close();
        } catch (IOException ex) {
            System.out.println("Couldn't create the file.");
        }
    }
}
