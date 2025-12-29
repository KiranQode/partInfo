Onshape Part Property Finder 🛠️
A Spring Boot utility that allows users to quickly retrieve physical properties (Mass, Volume) from an Onshape Part Studio by simply pasting the browser URL.

🌟 Features
URL Auto-Parsing: Paste a full Onshape URL; the app automatically extracts Document ID, Workspace ID, and Element ID using Regex.

Onshape API Integration: Secure communication with Onshape's REST API using Base64-encoded authentication.

Complex JSON Mapping: Handles nested "multi-body" data structures and extracts cumulative mass properties.

Responsive UI: Clean, modern interface built with Bootstrap and Thymeleaf.

🚀 Getting Started
1. Prerequisites: Java 17 or higher (Developed with Java 23), Maven 3.6+, Onshape API Keys(You must have an Access Key and Secret Key from the Onshape Developer Portal).

2. Configuration: Create or update src/main/resources/application.properties with your credentials:
	server.port=8080
	onshape.api.base-url=https://cad.onshape.com
	onshape.api.access-key=YOUR_ACCESS_KEY
	onshape.api.secret-key=YOUR_SECRET_KEY

3. Installation & Run
	Clone the repository:
	Bash
	git clone https://github.com/your-username/partInfo.git
	cd partInfo
	Build the project:

	Bash
	mvn clean install -DskipTests
	Run the application:

	Bash
	mvn spring-boot:run
	Access the tool: Open http://localhost:8080 in your browser.

4. Technical Architecture
	Backend Stack
	Spring Boot: Core framework for dependency injection and routing.
	Spring RestClient: Modern HTTP client for consuming the Onshape REST API.
	Jackson: Used for custom deserialization of nested Onshape mass properties.

	Frontend Stack
	Thymeleaf: Server-side template engine.
	Bootstrap 5: CSS framework for a responsive UI.
	JavaScript: Regex-based URL parser to handle Onshape browser links.

5. Data Mapping Logic
	Onshape returns mass properties in a nested "Bodies" format. This utility specifically targets the -all- key to provide the aggregate properties of the Part Studio:
	JSON
	{
	  "bodies": {
	    "-all-": {
	      "mass": [targetValue, minValue, maxValue],
	      "volume": [targetValue, minValue, maxValue]
	    }
	  }
	}
	The application extracts the targetValue (index 0) for display.

6. License
	This project is open-source and available under the MIT License.
