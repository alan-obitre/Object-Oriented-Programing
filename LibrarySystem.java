import java.util.ArrayList;

// ****** THE BOOK JAVE SCRIPT ******
class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.author = "Unknown";
        this.available = true;
    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book [ISBN=" + isbn +
                ", Title=" + title +
                ", Author=" + author +
                ", Available=" + available + "]";
    }
}

// ****** THE LOAN JAVA SCRIPT ******
class Loan {
    private Book book;
    private Member member;
    private String borrowDate;
    private String dueDate;

    public Loan(Book book, Member member, String borrowDate, String dueDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

        public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Loan [Book=" + book.getTitle() +
                ", Member=" + member.getName() +
                ", Borrow Date=" + borrowDate +
                ", Due Date=" + dueDate + "]";
    }
}

// ****** THE MEMBER JAVE SCRIPT ******
class Member {
    private String memberId;
    private String name;
    private ArrayList<Loan> loans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member [ID=" + memberId +
                ", Name=" + name +
                ", Active Loans=" + loans.size() + "]";
    }
}

// ****** THE LIBRARY JAVA SCRIPT ******
class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void lendBook(Book book, Member member,
                         String borrowDate, String dueDate) {

        if (book.isAvailable()) {
            Loan loan = new Loan(book, member, borrowDate, dueDate);
            member.addLoan(loan);
            book.setAvailable(false);

            System.out.println("Book lent successfully.");
        } else {
            System.out.println("Book is already on loan.");
        }
    }

    public void returnBook(Book book, Member member) {

        Loan loanToRemove = null;

        for (Loan loan : member.getLoans()) {
            if (loan.getBook() == book) {
                loanToRemove = loan;
                break;
            }
        }

        if (loanToRemove != null) {
            member.removeLoan(loanToRemove);
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Loan not found.");
        }
    }

    public void searchBookByTitle(String title) {

        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    @Override
    public String toString() {
        return "Library [Books=" + books.size() +
                ", Members=" + members.size() + "]";
    }
}

// ****** THE MAIN JAVA SCRIPT ******
public class LibrarySystem {
    public static void main(String[] args) {

        Library library = new Library();

        Book b1 = new Book("978-0131484100", "Introduction To Java Programming", "OBITRE ALAN");
        Book b2 = new Book("978-0590353427", "Essentials To Database Systems", "OLAN ALAN");

        Member m1 = new Member("VU-2303", "Alan@VU");

        library.addBook(b1);
        library.addBook(b2);
        library.registerMember(m1);

        library.lendBook(b1, m1, "15/06/2026", "30/06/2026");

        System.out.println(b1);
        System.out.println(m1);

        library.searchBookByTitle("Introduction To Java Programming");

        library.returnBook(b1, m1);

        System.out.println(b1);
    }
}