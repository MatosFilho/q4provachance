package q4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

	public static boolean validateUser(String[] entrada) {
		
		//Verifica se identificador válido
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(entrada[0]);
		if (matcher.find()) {
			System.out.println("Identificador invalido: " + entrada[0]);
			return false;
		}
		
		//Verifica se data de nascimento válida
		pattern = Pattern.compile("[^0-9/]");
		matcher = pattern.matcher(entrada[2]);
		if (matcher.find()) {
			System.out.println("Data de nascimento invalida: " + entrada[2]);
			return false;
		}
		String[] camposData = entrada[2].split("/");
		if (camposData.length != 3) {
			System.out.println("Data de nascimento invalida: " + entrada[2]);
			return false;
		}
		int dia = Integer.parseInt(camposData[0]);
		int mes = Integer.parseInt(camposData[1]);
		int ano = Integer.parseInt(camposData[2]);
		if ((dia > 31 || dia < 1) || (mes < 1 || mes > 12) || (ano < 1900 || ano > 1999)) {
			System.out.println("Data de nascimento invalida: " + entrada[2]);
			return false;
		}
		
		//Verifica se nome válido
		pattern = Pattern.compile("[^a-zA-Z ]");
		matcher = pattern.matcher(entrada[1]);
		if (matcher.find()) {
			System.out.println("Nome invalido: " + entrada[1]);
			return false;
		}
		
		//Verifica se naturalidade válida
		matcher = pattern.matcher(entrada[3]);
		if (matcher.find()) {
			System.out.println("Naturalidade invalida: " + entrada[3]);
			return false;
		}
		
		//Verifica se nacionalidade válida
		matcher = pattern.matcher(entrada[4]);
		if (matcher.find()) {
			System.out.println("Nacionalidade invalida: " + entrada[4]);
			return false;
		}
		
		return true;
	}
	
	public static Object changeArraySize (Object obj, int len) {
		Class<?> arr = obj.getClass().getComponentType();
		Object newArray = Array.newInstance(arr, len);
		
		int co = Array.getLength(obj);
		System.arraycopy(obj, 0, newArray, 0, co);
		return newArray;
	}
	
    public static void main(String[] args) {

        //String csvFile = "/Users/mkyong/csv/users.csv";
        //String csvFile = "/Users/Matos/csv/users.csv";
    	String csvFile = "users.csv";
    	String line = "";
        String cvsSplitBy = ",";
        User[] users = new User[2];
        int i = 0;
        
        //verifica se o arquivo tem um caminho válido
        Pattern pattern = Pattern.compile("[^0-9a-zA-Z._/]");
        Matcher matcher = pattern.matcher(csvFile);
        if (matcher.find()) {
        	System.out.println("Erro na tentativa de abrir arquivo csv.");
        }
        
        else {
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	while ((line = br.readLine()) != null && i <= users.length) {
	                // use comma as separator
	                String[] usersLine = line.split(cvsSplitBy);
	                //Verifica se existem cinco campos e se os campos são válidos
	                if (usersLine.length == 5 && validateUser(usersLine)) {
		                User user = new User((usersLine[0]), usersLine[1], usersLine[2], 
		                		usersLine[3], usersLine[4]);
		                //Verifica se o vetor está cheio e o aumenta em tempo de execução
		                if (users.length <= i) {
		                	users = (User[]) changeArraySize(users, users.length*2); 
		                	System.out.println("Tamanho aumentado para: " + users.length);
		                }
		                users[i]=user;
		                i++;
	                }
	            }
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        System.out.println ("Imprimindo usuários...");
        for (int k = 0; k < i; k++) {
        	System.out.println("Usuario " + (k+1));
        	System.out.println("id: " + users[k].getId());
        	System.out.println("nome: " + users[k].getNome());
        	System.out.println("data de nascimento: " + users[k].getData_de_nascimento());
        	System.out.println("naturalidade: " + users[k].getNaturalidade());
        	System.out.println("nacionalidade: " + users[k].getNacionalidade());
        }
        
    }
}