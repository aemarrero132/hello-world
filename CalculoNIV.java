package niv.calculos;

import java.util.Hashtable;

public class CalculoNIV {
	private static int LONGITUD_NIV = 17;
	private Hashtable<Character, Integer> letras;
	//private Hashtable<String, Character> anosprod;

	//Constructor
	public CalculoNIV() {
		inicializar();
	}
	
	private void inicializar() {
		//Inicializacion de Valores de Letras en Tabla de Hash
		letras = new Hashtable<Character, Integer>();
		letras.put('A', 1);	letras.put('B', 2);
		letras.put('C', 3); letras.put('D', 4);
		letras.put('E', 5); letras.put('F', 6);
		letras.put('G', 7); letras.put('H', 8);
		letras.put('J', 1); letras.put('K', 2);
		letras.put('L', 3); letras.put('M', 4);
		letras.put('N', 5); letras.put('P', 7);
		letras.put('R', 9); letras.put('S', 2);
		letras.put('T', 3); letras.put('U', 4);
		letras.put('V', 5); letras.put('W', 6);
		letras.put('X', 7); letras.put('Y', 8);
		letras.put('Z', 9);
	}
	
	//Retorna el digito 9 verificador de acuerdo a su parte decimal
	public char digito_Verificador(double resultado) {
		double parte_decimal = resultado % 1; //Elimina la parte entera.
		char digito_verificador = '0';
		
		System.out.println("Valor Decimal: " + parte_decimal);
		
		//Asignacion de digito verificador de acuerdo al valor decimal
		if(parte_decimal >= 0 && parte_decimal <= 0.08) {
			digito_verificador = '0';
		}else if(parte_decimal >= 0.09 && parte_decimal <= 0.17) {
			digito_verificador = '1';
		}else if(parte_decimal >= 0.18 && parte_decimal <= 0.26) {
			digito_verificador = '2';
		}else if(parte_decimal >= 0.27 && parte_decimal <= 0.35) {
			digito_verificador = '3';
		}else if(parte_decimal >= 0.36 && parte_decimal <= 0.44) {
			digito_verificador = '4';
		}else if(parte_decimal >= 0.45 && parte_decimal <= 0.53) {
			digito_verificador = '5';
		}else if(parte_decimal >= 0.54 && parte_decimal <= 0.62) {
			digito_verificador = '6';
		}else if(parte_decimal >= 0.63 && parte_decimal <= 0.71) {
			digito_verificador = '7';
		}else if(parte_decimal >= 0.72 && parte_decimal <= 0.80) {
			digito_verificador = '8';
		}else if(parte_decimal >= 0.81 && parte_decimal <= 0.89) {
			digito_verificador = '9';
		}else if(parte_decimal >= 0.90 && parte_decimal <= 0.99) {
			digito_verificador = 'X';
		}
		System.out.println("digito verificador: " + digito_verificador);
		return digito_verificador;
	}
	
	//Realiza el calculo matematico para generar la sumatoria y producto de los valores para el digito verificador
	public double sumatoriaNIV(StringBuffer serial) {
		char ch;
		double sumatoria = 0;
		double resultado;
		
		int factores[] = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };	//factores
		int valores[]= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; 	//vector de operacion
		
		//Traduccion de los valores de las letras.
		for(int i = 0; i < LONGITUD_NIV; i++) {
			ch = serial.charAt(i);
			if(Character.isLetter(ch)) {
				valores[i] = letras.get(ch);
			}
			else {
				valores[i] = Character.getNumericValue(ch);
			}
		}
		//Sumatoria de valores y multiplicacion con factores respectivos.
		for(int i = 0; i < LONGITUD_NIV; i++) {
			sumatoria = sumatoria + (valores[i] * factores[i]);
		}
		resultado = (sumatoria / 11);
		return resultado;
	}
	
	//Calcula el Identificador Individual Vehicular (IIV) - Digitos 14 al 17 
	public String calculo_IIV(String serial) {
		String cifra_correlativa = null;
		String iiv = null;
		int valor;
		
		//calcula proximo correlativo
		valor = Integer.parseInt(serial);
		cifra_correlativa = String.valueOf(valor + 1);	
		
		//Se agrega la cantidad de ceros para formar todo el correlativo
		if(cifra_correlativa.length() == 1) {
			iiv = "000" + cifra_correlativa; 
		}
		else if (cifra_correlativa.length() == 2) {
			iiv = "00" + cifra_correlativa;
		}
		else if (cifra_correlativa.length() == 3) {
			iiv = "0" + cifra_correlativa;
		}
		else if (cifra_correlativa.length() == 4) {
			iiv = cifra_correlativa;
		}
		
		System.out.println("IIV: " + iiv);
		return iiv;
	}
	
}
