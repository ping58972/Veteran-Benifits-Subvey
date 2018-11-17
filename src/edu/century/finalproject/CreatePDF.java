package edu.century.finalproject;
/**public class CreatePDF from the package edu.century.finalproject
 * 
 *  Century College, CSCI 2082 Fall 2018.
 *  CreatePDF.java, Programming Final Project.
 *  
 *  @author (Ping) Nalongsone Danddank
 *  @version 1.0
 *  @since 11/16/2018
 * */
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class CreatePDF {
	
	private static final String DEST = "PDF/Response.pdf";
	private String filePath;
	private String userName;
	private PdfWriter writer;
	private PdfDocument pdf;
	private Document document;
	private Table table;
	private int count;
	
	/*public CreatePDF(String userName)
	 * @Descriptions 
	 * 		to construct and set user name by defeat file path. 
	 * @Parameter: 
	 * 		userName - String.
	 * @Precondition : userName not null.
	 * @Postcondition
	 * @Return
	 * @Thorws 	: null exception.
	 * */
	public CreatePDF(String userName) {
	setFilePath(DEST);
	this.userName = userName;
	initPdf(filePath, userName);
	}
	
	/*public CreatePDF(String filePath, String userName)
	 * @Descriptions 
	 * 		to construct and set user name and file path. 
	 * @Parameter: 
	 * 		filePath - String.
	 * 		userName - String.
	 * @Precondition : userName and filePath not null.
	 * @Postcondition
	 * @Return
	 * @Thorws 	: null exception.
	 * */
	public CreatePDF(String filePath, String userName) {
		setFilePath(filePath);
		this.userName = userName;
		initPdf(filePath, userName);
	}
	
	/* public void setFilePath(String dest)
	 * @Descriptions 
	 * 		to set dest to FilePath. 
	 * @Parameter : dest - String
	 * @Precondition : dest must not null.
	 * @Postcondition
	 * @Return 
	 * @Thorws 	
	 * */
	public void setFilePath(String dest) {
		this.filePath = dest;
	}
	
	/* public void initPdf(String dest, String userName)
	 * @Descriptions 
	 * 		to initiate PDF document to create a pdf file. 
	 * @Parameter : 
	 * 		dest - String
	 * 		userName - String
	 * @Precondition : dest  and userName must not null.
	 * @Postcondition
	 * @Return 
	 * @Thorws 	: FileNotFoundException
	 * */
	public void initPdf(String dest, String userName){
		//Initialize PDF writer
        try {
			writer = new PdfWriter(dest);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Initialize PDF document
        pdf = new PdfDocument(writer);
        // Initialize document
        document = new Document(pdf);
        document.add(new Paragraph("USER NAME:" + userName));
        count = 0;
        table = new Table(3);
        
	}
	
	/* public void add(String question, String answer)
	 * @Descriptions 
	 * 		to add questions and answers to pdf file. 
	 * @Parameter : 
	 * 		question - String
	 * 		answer - String
	 * @Precondition : answer  and question must not null.
	 * @Postcondition
	 * @Return 
	 * @Thorws 	:
	 * */
	public void add(String question, String answer) {	
        table.setWidth(UnitValue.createPercentValue(100));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(++count))));
        table.addCell(new Cell().add(new Paragraph(question)));
        table.addCell(new Cell().add(new Paragraph(answer)));     
	}
	
	/* public void openPDF(String lastQ)
	 * @Descriptions 
	 * 		add the last question and open the pdf file. 
	 * @Parameter : 
	 * 		lastQ - String
	 * @Precondition : lastQ must not null.
	 * @Postcondition
	 * @Return 
	 * @Thorws 	: IOException
	 * */
	public void openPDF(String lastQ) {
		document.add(table);
		document.add(new Paragraph("You end with: "+ lastQ));
		//Close document
		document.close();
		   if (Desktop.isDesktopSupported()) {
			   File file = new File(filePath);
		        file.getParentFile().mkdirs();
	        	try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
}
