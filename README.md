# SalamTransit

SalamTransit is a comprehensive medical transportation web application designed to provide safe, efficient, and reliable transportation services for healthcare needs in Columbus, Ohio. Utilizing Java, PostgreSQL, and Vue 3, SalamTransit aims to enhance accessibility to medical facilities for those in need.

## Features

- Schedule and manage rides to and from medical appointments.
- Real-time tracking of transportation vehicles.
- Secure login and authentication for users.
- Intuitive and responsive user interface for an optimal user experience.

## Getting Started

### Prerequisites

- Java JDK 11 or later
- PostgreSQL 10 or later
- Node.js 12 or later

### Setting Up the Development Environment

#### Backend

1. Clone the repository to your local machine.
2. Navigate to the `backend` directory.
3. Run `./mvnw spring-boot:run` to start the Spring Boot application.

#### Frontend

1. Navigate to the `frontend` directory.
2. Run `npm install` to install all required dependencies.
3. Run `npm run dev` to start the Vue.js development server.

### Database Setup

1. Ensure PostgreSQL is running on your local machine.
2. Navigate to the `database` directory and run `./create.sh` to set up the database schema and initial data.

## Usage

After setting up both the backend and frontend:

1. Open your web browser and navigate to `http://localhost:9000` to access the SalamTransit application.
2. Log in or register a new account to start scheduling medical transportation services.

## Contributing

We welcome contributions to the SalamTransit project. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details.

## Acknowledgments

- Special thanks to all contributors who have helped to build SalamTransit into a valuable resource for the community.
