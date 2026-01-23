# Crop Recommendation System

An intelligent crop recommendation system that uses multiple AI techniques including Fuzzy Logic, Ontologies, and Bayesian Networks to predict suitable crops based on soil characteristics.

## 🌱 Features

- **Multi-technique AI approach**: Combines fuzzy logic, ontological reasoning, and Bayesian networks for accurate predictions
- **Soil data management**: Add, modify, and manage soil sample data
- **Crop prediction**: Get crop recommendations based on soil parameters
- **Inference system**: Reasoning engine for crop suitability analysis
- **Validation**: K-Fold cross-validation for model accuracy assessment

## 🛠️ Technologies Used

- **Java 18**
- **Maven** - Build automation and dependency management
- **jFuzzyLite** - Fuzzy logic inference
- **OWL API** - Ontology management
- **jSMILE** - Bayesian networks
- **Swing** - GUI framework

## 📋 Prerequisites

- Java JDK 18 or higher
- Maven 3.6+
- Git

## 🚀 Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/cropRecomendation.git
cd cropRecomendation
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn exec:java
```

## 📁 Project Structure

```
cropRecomendation/
├── src/
│   └── main/
│       └── java/
│           ├── com/mycompany/plantrecom/    # Main application entry
│           ├── View/                         # GUI components
│           ├── controller/                   # Controller classes
│           ├── Model/                        # Data models and logic
│           ├── Reseaubayesien/              # Bayesian network implementation
│           ├── Ontologie/                    # Ontology files (OWL)
│           └── Dataset/                      # Training datasets
├── pom.xml                                   # Maven configuration
└── README.md
```

## 💡 Usage

1. Launch the application - the Homepage will open
2. Add soil data with parameters like N, P, K levels, pH, temperature, humidity, rainfall
3. Use the prediction feature to get crop recommendations
4. View inference results based on the AI reasoning system

## 🧪 Validation

The system includes K-Fold cross-validation to assess model performance and accuracy.

## 📊 Dataset

The system uses soil characteristics including:
- Nitrogen (N) content
- Phosphorus (P) content  
- Potassium (K) content
- pH level
- Temperature
- Humidity
- Rainfall

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👥 Authors

- Ishak Riali, Messaouda Fareh, Youcef Benkessirat, Aymen Zebayri

## 🙏 Acknowledgments

- Thanks to the developers of jFuzzyLite, OWL API, and jSMILE
- Agricultural research data sources
