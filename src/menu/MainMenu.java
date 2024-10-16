package menu;

import database.*;
import classes.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private static DBManager dbManager;

    public static void startMenu() throws SQLException {
        ConnectDB connectDB = new ConnectDB(); // Assuming ConnectDB handles connections
        Connection connection = connectDB.getConnection();
        dbManager = new DBManager(connectDB);

        //DB_Initializer dbInitializer = new DB_Initializer(connection);
        //dbInitializer.createTables(); // Initialize tables if not exists

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Add Genre");
            System.out.println("2. Add Author");
            System.out.println("3. Add Label");
            System.out.println("4. Add Song");
            System.out.println("5. View Genre");
            System.out.println("6. View Author");
            System.out.println("7. View Label");
            System.out.println("8. View Song");
            System.out.println("9. Update Genre");
            System.out.println("10. Update Author");
            System.out.println("11. Update Label");
            System.out.println("12. Update Song");
            System.out.println("13. Delete Genre");
            System.out.println("14. Delete Author");
            System.out.println("15. Delete Label");
            System.out.println("16. Delete Song");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addGenre(scanner);
                    break;
                case 2:
                    addAuthor(scanner);
                    break;
                case 3:
                    addLabel(scanner);
                    break;
                case 4:
                    addSong(scanner);
                    break;
                case 5:
                    viewGenre(scanner);
                    break;
                case 6:
                    viewAuthor(scanner);
                    break;
                case 7:
                    viewLabel(scanner);
                    break;
                case 8:
                    viewSong(scanner);
                    break;
                case 9:
                    updateGenre(scanner);
                    break;
                case 10:
                    updateAuthor(scanner);
                    break;
                case 11:
                    updateLabel(scanner);
                    break;
                case 12:
                    updateSong(scanner);
                    break;
                case 13:
                    deleteGenre(scanner);
                    break;
                case 14:
                    deleteAuthor(scanner);
                    break;
                case 15:
                    deleteLabel(scanner);
                    break;
                case 16:
                    deleteSong(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void addGenre(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer before reading the full line
        System.out.print("Enter genre name: ");
        String name = scanner.nextLine(); // Use nextLine to allow spaces
        Genre genre = new Genre(name);
        dbManager.insertGenre(genre);
    }

    private static void addAuthor(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer before reading the full line
        System.out.print("Enter author name: ");
        String name = scanner.nextLine(); // Use nextLine to allow spaces
        Genre genre = null;
        Label label = null;
        // Loop until valid genre and label IDs are entered
        while (genre == null) {
            System.out.print("Enter genre ID: ");
            int genreId = scanner.nextInt();
            genre = dbManager.getGenreById(genreId);
            if (genre == null) {
                System.out.println("Invalid genre ID.");
                return;
            }
        }
        while (label == null) {
            System.out.print("Enter label ID: ");
            int labelId = scanner.nextInt();
            label = dbManager.getLabelById(labelId);
            if (label == null) {
                System.out.println("Invalid label ID.");
                return;
            }
        }
        Author author = new Author(label, name, genre);
        dbManager.insertAuthor(author);
    }

    private static void addLabel(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer before reading the full line
        System.out.print("Enter label name: ");
        String name = scanner.nextLine(); // Use nextLine to allow spaces
        System.out.print("Enter location: ");
        String location = scanner.nextLine(); // Use nextLine to allow spaces
        Label label = new Label(name, location);
        dbManager.insertLabel(label);
    }

    private static void addSong(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer before reading the full line
        System.out.print("Enter song name: ");
        String name = scanner.nextLine(); // Use nextLine to allow spaces

        Author author = null;
        while (author == null) {
            System.out.print("Enter author ID: ");
            int authorId = scanner.nextInt();
            author = dbManager.getAuthorById(authorId);
            if (author == null) {
                System.out.println("Error: Invalid author ID.");
                return;
            }
        }

        System.out.print("Enter song duration (minutes, seconds): ");
        int minutes = scanner.nextInt();
        int seconds = scanner.nextInt();

        Song song = new Song(author, name, minutes, seconds);
        dbManager.insertSong(song);
        System.out.println("Song added successfully!");
    }

    private static void viewGenre(Scanner scanner) {
        Genre genre = null;
        while (genre == null) {
            System.out.print("Enter genre ID: ");
            int id = scanner.nextInt();
            genre = dbManager.getGenreById(id);
            if (genre != null) {
                System.out.println("Genre: " + genre.getName());
            } else {
                System.out.println("Error: Invalid genre ID.");
                return;
            }
        }
    }

    private static void viewAuthor(Scanner scanner) {
        Author author = null;
        while (author == null) {
            System.out.print("Enter author ID: ");
            int id = scanner.nextInt();
            author = dbManager.getAuthorById(id);
            if (author != null) {
                System.out.println("Author: " + author.getName() +
                        ", Genre: " + author.getGenre().getName() +
                        ", Label: " + author.getLabel().getName());
            } else {
                System.out.println("Error: Invalid author ID.");
                return;
            }
        }
    }

    private static void viewLabel(Scanner scanner) {
        Label label = null;
        while (label == null) {
            System.out.print("Enter label ID: ");
            int id = scanner.nextInt();
            label = dbManager.getLabelById(id);
            if (label != null) {
                System.out.println("Label: " + label.getName() + ", Location: " + label.getLocation());
            } else {
                System.out.println("Error: Invalid label ID.");
                return;
            }
        }
    }

    private static void viewSong(Scanner scanner) {
        Song song = null;
        while (song == null) {
            System.out.print("Enter song ID: ");
            int id = scanner.nextInt();
            song = dbManager.getSongById(id);
            if (song != null) {
                System.out.println("Song: " + song.getName() + ", Author: " + song.getAuthor().getName() + ", Duration: ");
                song.outputDuration();
            } else {
                System.out.println("Error: Invalid song ID.");
                return;
            }
        }
    }

    private static void updateGenre(Scanner scanner) {
        Genre genre = null;
        while (genre == null) {
            System.out.print("Enter genre ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            genre = dbManager.getGenreById(id);
            if (genre != null) {
                System.out.print("Enter new genre name: ");
                String name = scanner.nextLine();
                genre.setName(name);
                dbManager.updateGenre(genre);
                System.out.println("Genre updated successfully.");
            } else {
                System.out.println("Error: Invalid genre ID.");
                return;
            }
        }
    }

    private static void updateAuthor(Scanner scanner) {
        Author author = null;
        while (author == null) {
            System.out.print("Enter author ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            author = dbManager.getAuthorById(id);
            if (author != null) {
                System.out.print("Enter new author name: ");
                String name = scanner.nextLine();

                Genre genre = null;
                while (genre == null) {
                    System.out.print("Enter new genre ID: ");
                    int genreId = scanner.nextInt();
                    genre = dbManager.getGenreById(genreId);
                    if (genre == null) {
                        System.out.println("Error: Invalid genre ID. Please try again.");
                    }
                }

                Label label = null;
                while (label == null) {
                    System.out.print("Enter new label ID: ");
                    int labelId = scanner.nextInt();
                    label = dbManager.getLabelById(labelId);
                    if (label == null) {
                        System.out.println("Error: Invalid label ID. Please try again.");
                    }
                }

                author.setName(name);
                author.setGenre(genre);
                author.setLabel(label);
                dbManager.updateAuthor(author);
                System.out.println("Author updated successfully.");
            } else {
                System.out.println("Error: Invalid author ID.");
                return;
            }
        }
    }

    private static void updateLabel(Scanner scanner) {
        Label label = null;
        while (label == null) {
            System.out.print("Enter label ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            label = dbManager.getLabelById(id);
            if (label != null) {
                System.out.print("Enter new label name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new location: ");
                String location = scanner.nextLine();
                label.setName(name);
                label.setLocation(location);
                dbManager.updateLabel(label);
                System.out.println("Label updated successfully.");
            } else {
                System.out.println("Error: Invalid label ID.");
                return;
            }
        }
    }

    private static void updateSong(Scanner scanner) {
        Song song = null;
        while (song == null) {
            System.out.print("Enter song ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            song = dbManager.getSongById(id);
            if (song != null) {
                System.out.print("Enter new song name: ");
                String name = scanner.nextLine();

                Author author = null;
                while (author == null) {
                    System.out.print("Enter new author ID: ");
                    int authorId = scanner.nextInt();
                    author = dbManager.getAuthorById(authorId);
                    if (author == null) {
                        System.out.println("Error: Invalid author ID. ");
                        return;
                    }
                }

                System.out.print("Enter new song duration (minutes, seconds): ");
                int minutes = scanner.nextInt();
                int seconds = scanner.nextInt();

                song.setName(name);
                song.setDuration(minutes, seconds);
                song.setAuthor(author);
                dbManager.updateSong(song);
                System.out.println("Song updated successfully.");
            } else {
                System.out.println("Error: Invalid song ID.");
                return;
            }
        }
    }

    private static void deleteGenre(Scanner scanner) {
        System.out.print("Enter genre ID to delete: ");
        int id = scanner.nextInt();
        Genre genre = dbManager.getGenreById(id);
        if (genre != null) {
            dbManager.deleteGenreById(id);
            System.out.println("Genre deleted successfully.");
        } else {
            System.out.println("Error: Invalid genre ID.");
            return;
        }
    }

    private static void deleteAuthor(Scanner scanner) {
        System.out.print("Enter author ID to delete: ");
        int id = scanner.nextInt();
        Author author = dbManager.getAuthorById(id);
        if (author != null) {
            dbManager.deleteAuthorById(id);
            System.out.println("Author deleted successfully.");
        } else {
            System.out.println("Error: Invalid author ID.");
            return;
        }
    }

    private static void deleteLabel(Scanner scanner) {
        System.out.print("Enter label ID to delete: ");
        int id = scanner.nextInt();
        Label label = dbManager.getLabelById(id);
        if (label != null) {
            dbManager.deleteLabelById(id);
            System.out.println("Label deleted successfully.");
        } else {
            System.out.println("Error: Invalid label ID.");
            return;
        }
    }

    private static void deleteSong(Scanner scanner) {
        System.out.print("Enter song ID to delete: ");
        int id = scanner.nextInt();
        Song song = dbManager.getSongById(id);
        if (song != null) {
            dbManager.deleteSongById(id);
            System.out.println("Song deleted successfully.");
        } else {
            System.out.println("Error: Invalid song ID.");
            return;
        }
    }
}
