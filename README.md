# Image Service

## Description

Image Service is responsible for uploading and managing profile images for cryptocurrencies (a separate service altogether). The application is prepared to handle various images and types in the future, but currently, it is limited to a demo for uploading the main (profile) picture only.

## Requirements

To build and run the application, you need:

- JDK 22 or higher
- Apache Maven
- Spring Boot 3.3.0 or higher
- Docker (for running the MongoDB database)

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Memtize/memtize-image-service.git
    cd memtize-image-service
    ```

2. **Set up MongoDB with Docker**:
    ```bash
    docker run --name cryptoImages -p 27017:27017 -e MONGO_INITDB_DATABASE=cryptoImages -v mongo-data:/data/cryptoImages -d mongo
    ```

3. **Build the application**:
    ```bash
    mvn clean install
    ```

4. **Run the application with default profile**:


## Usage

The application exposes the following endpoints:

- `GET /api/crypto/{cryptoId}/pictures/main` - Retrieve the main profile picture by crypto ID.
- `POST /api/crypto/{cryptoId}/pictures/main` - Upload the main profile picture for a given crypto ID.
- `DELETE /api/crypto/{cryptoId}/pictures/main` - Delete the main profile picture for a given crypto ID.

## Future Improvements

- Support for multiple image types and categories.
- Enhanced image management features.
