package DynamicVariables;

import Urls.Urls;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class DynamicVarialbles {

    public static String tocan;
    public static String referenceId;
    public static String fetchQuoteLinks;
    public static String premiumResultId;
    public static String proposalId;
    public static String policyNumber;
    public static String IDforRenewal;
    public static String  newReferenceId;
    public static String newProposalId;
    public static String IDforCancel;



  public static void getPremiumResultId() {
      String[] s = fetchQuoteLinks.split("com");
      Urls.premiumResultId=s[1];
}


    public static JSONObject fileLoader(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject;
    }

    public static JSONObject craeteQuotePayload() throws IOException {
        JSONObject f = fileLoader("src/main/java/PayLoads/quotesCreate.json");
        return f;
    }

    public static JSONObject craeteProposalPayload() throws IOException {
        JSONObject f = fileLoader("src/main/java/PayLoads/createProposal.json");
        f.getJSONObject("data").put("referenceId",referenceId);
        f.getJSONObject("data").put("premiumResultId",premiumResultId);
        return f;

    }
    public static JSONObject craeteTransactionPayload() throws IOException {
        int length = 18;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        JSONObject f = fileLoader("src/main/java/PayLoads/transaction.json");
        //  String referenceId=f.getJSONObject("data").getString("referenceId");
        f.getJSONObject("data").put("referenceId",referenceId);
        f.getJSONObject("data").put("proposalId",proposalId);
        f.getJSONObject("data").put("fabSerialNumber",randomString.toString());
        return f;

    }
    public static JSONObject craeteRenewalTransactionPayload() throws IOException {
        int length = 18;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        JSONObject f = fileLoader("src/main/java/PayLoads/transaction.json");
        //  String referenceId=f.getJSONObject("data").getString("referenceId");
        f.getJSONObject("data").put("referenceId",newReferenceId);
        f.getJSONObject("data").put("proposalId",newProposalId);
        f.getJSONObject("data").put("fabSerialNumber",randomString.toString());
        return f;

    }
    public static JSONObject craeteCancellationPayload() throws IOException {
        JSONObject f = fileLoader("src/main/java/PayLoads/cancellation.json");
        //  String referenceId=f.getJSONObject("data").getString("referenceId");
        f.getJSONObject("data").put("policyNumber",IDforCancel);
        return f;

    }



    public static JSONObject craeteRenewalPayload() throws IOException {
        JSONObject f = fileLoader("src/main/java/PayLoads/renewal.json");
        f.getJSONObject("data").put("referenceId",IDforRenewal);
        return f;
  }

                                   //******************* travel *******************
                                    //******************* travel *******************

    public static JSONObject craeteQuotePayloadTravel() throws IOException {
        JSONObject f = fileLoader("/Users/shrinivaslondhe/Desktop/APIAutomation/src/main/java/PayLoads/quoteCreateTravel.json");
        return f;
    }


    public static JSONObject craeteProposalTravelPayload() throws IOException {
        JSONObject f = fileLoader("/Users/shrinivaslondhe/Desktop/APIAutomation/src/main/java/PayLoads/createProposalTravel.json");
        f.getJSONObject("data").put("referenceId",referenceId);
        f.getJSONObject("data").put("premiumResultId",premiumResultId);
        return f;

    }
    public static JSONObject craeteTransactionTravelPayload() throws IOException {
        int length = 18;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        JSONObject f = fileLoader("/Users/shrinivaslondhe/Desktop/APIAutomation/src/main/java/PayLoads/transactionTravel.json");
        //  String referenceId=f.getJSONObject("data").getString("referenceId");
        f.getJSONObject("data").put("referenceId",referenceId);
        f.getJSONObject("data").put("proposalId",proposalId);
        f.getJSONObject("data").put("fabSerialNumber",randomString.toString());
        return f;

    }
    public static JSONObject craeteCancellationTravelPayload() throws IOException {
        JSONObject f = fileLoader("/Users/shrinivaslondhe/Desktop/APIAutomation/src/main/java/PayLoads/cancellationTravel.json");
        //  String referenceId=f.getJSONObject("data").getString("referenceId");
        f.getJSONObject("data").put("policyNumber",policyNumber);
        return f;

    }












    public static void main(String[] args) throws IOException {
        JSONObject f = fileLoader("src/main/java/PayLoads/createProposal.json");
        String referenceId=f.getJSONObject("data").getString("referenceId");
        System.out.println("before chanage = "+f.toString(4));
        f.getJSONObject("data").put("referenceId","cvvvv");
        System.out.println("After chanage = "+f.toString(4));

        System.out.println(referenceId);
    }
}
