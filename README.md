# Star Wars Android App

This is a Star Wars-themed Android app built with Kotlin and Jetpack Compose. The app includes features such as character search, character details, and displays the characters' information retrieved from the SWAPI (Star Wars API). It demonstrates modern Android architecture and scalability considerations.

## Features

- **Character Search**: Search for characters by name.
- **Character Details**: View detailed information for a character.
- **Paging Support**: Efficient loading of character data using Paging3.
- **Modern Android Architecture**: The app uses MVVM architecture with Hilt for Dependency Injection.
- **Error Handling**: Uses a custom error handler to manage different types of errors.

## Technologies Used

- **Kotlin**: Modern programming language for Android development.
- **Jetpack Compose**: Declarative UI toolkit for building the app's interface.
- **Paging3**: Efficient pagination for loading large datasets.
- **Hilt**: Dependency Injection framework.
- **Coroutines**: For managing background tasks and async functions and parallel requests.
- **Retrofit**: Networking library to interact with the SWAPI.
- **JUnit and MockK**: For unit testing and mocking.

## Architecture

The app follows the **MVVM** (Model-View-ViewModel) architecture with clean separation between UI, business logic, and data layers. Here's an overview of the architecture:

- **UI Layer**: Uses Jetpack Compose for building the UI.
- **Domain Layer**: Contains use cases for business logic (e.g., `SearchCharactersUseCase`).
- **Data Layer**: Manages data from remote sources (API calls).

### Main Components

1. **SearchCharactersUseCase**: A use case that fetches data from the repository and transforms it into a format that the UI layer can consume. It implements the paging functionality.
2. **Repository**: Handles data fetching from the remote data source (SWAPI) and any other required data manipulation.
3. **PagingSource**: Used for paginated loading of data from SWAPI to optimize the app’s performance.

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/starwars-app.git
2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Run the app on an emulator or a physical device.

## How to Use the App
1. Open the app and type the name of a character in the search bar (e.g., "Luke").
2. The app will display a list of characters matching the query.
3. Click on any character to view detailed information about them, including films, birth year, and more.

## Improvements
1. Because of lack of time, just one unit test was added for SearchCharactersUseCase.kt class. It could (should) have more tests (unit/integration/ui).
2. For simplicity, ignored modularization based on gradle modules, just used packaging.
3. It could be first-offline and also have caching system.
