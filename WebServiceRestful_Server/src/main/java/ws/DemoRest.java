package ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//based on https://www.youtube.com/watch?v=vJvD1-0N3AU&ab_channel=LearningProgramming
//Link to website: http://localhost:8080/WebServiceRestful_Server/primeservice.html


@WebServlet("/prime")
public class DemoRest extends HttpServlet{

	String primePath = "C:\\Users\\carol\\eclipse-workspace\\WebServiceRestful_Server\\src\\primes.txt";
	String nonPrimePath = "C:\\Users\\carol\\eclipse-workspace\\WebServiceRestful_Server\\src\\nonprimes.txt";

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int number = Integer.parseInt(req.getParameter("num"));
		PrintWriter out = res.getWriter();
		out.print(isPrime(number));
	

	}


	private String isPrime(int a) {
	
		if (isPrimeFile(a))
			return "PrimeNumber";

		
		if(isNoPrimeFile(a)) 
			return "No PrimeNumber";
		 
		if (calcPrime(a)) {
			writePrimes(a);
			return "PrimeNumber";
		} else {
			writeNoPrimes(a); 
			return "No PrimeNumber";
		}
	}

	private boolean isPrimeFile(int a) {

		// https://www.w3schools.com/java/java_files_read.asp
		File file = new File(primePath);
		Scanner scanner = null;
		String data = "";
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			data = scanner.nextLine();
		}
		scanner.close();
		String[] primes = data.split(",");

		for (int i = 0; i < primes.length; i++) {
			if (a == Integer.parseInt(primes[i]))
				return true;
		}

		return false;
	}

	private boolean isNoPrimeFile(int a) {
		// https://www.w3schools.com/java/java_files_read.asp
		File file = new File(nonPrimePath);
		Scanner scanner = null;
		String data = "";
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			data = scanner.nextLine();
		}
		scanner.close();
		String[] primes = data.split(",");

		for (int i = 0; i < primes.length; i++) {
			if (a == Integer.parseInt(primes[i]))
				return true;
		}

		return false;
	}

	private boolean calcPrime(int a) {
		for (int i = 2; i <= a / 2; i++) {
			if (a % i == 0) {
				return false;
			}
		}

		return true;
	}

	private void writePrimes(int a) {
		FileWriter writer;
		try {
			writer = new FileWriter(primePath, true);
			writer.append("," + Integer.toString(a));
			writer.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeNoPrimes(int a) {
		FileWriter writer;
		try {
			writer = new FileWriter(nonPrimePath, true);
			writer.append("," + Integer.toString(a));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
