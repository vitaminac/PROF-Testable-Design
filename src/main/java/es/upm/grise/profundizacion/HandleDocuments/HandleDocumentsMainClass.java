package es.upm.grise.profundizacion.HandleDocuments;

public class HandleDocumentsMainClass {
	private static final String ENVIRON = "APP_HOME";

	public static void main(String[] args) {

		// Previous code does not matter ....
		// You can assume in particular that parameters have been checked

		// The document elements
		String TEMPLATE = args[1].toUpperCase();
		String TITLE = args[3];
		String AUTHOR = args[5];
		String BODY = args[7];

		try {
			// If ENVIRON does not exist, null is returned
			final String path = System.getenv(ENVIRON);
			final MySQLHelperFactory mySQLHelperFactory = new MySQLHelperFactory();
			final MySQLHelper mySQLHelper = mySQLHelperFactory.createMySQLHelper(new ConfigProvider(path), new ReflectionWrapper());
			final DocumentIdProvider documentIdProvider = new DocumentIdProvider(mySQLHelper);
			final DocumentFactory documentFactory = new DocumentFactory(documentIdProvider);
			Document document = documentFactory.createDocument();
			document.setTemplate(TEMPLATE);
			document.setAuthor(AUTHOR);
			document.setTitle(TITLE);
			document.setBody(BODY);
			System.out.println(document.getFormattedDocument());

			// What follows does not matter either ....

			// Exit without error
			System.exit(0);

		} catch (RecoverableError e) {

			// Exit with error
			System.exit(1);

		} catch (NonRecoverableError e) {

			// Exit with error
			System.exit(1);

		}


	}

}
