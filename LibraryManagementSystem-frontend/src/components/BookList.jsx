import BookCard from "./BookCard";
import "./BookList.css"

function BookList() {

    const dummyBooks = [
        {
            id: 1,
            title: "Clean Code",
            author: "Robert C. Martin",
            isbn: "978-0132350884",
            copies: 5,
            available: 3,
            image_url: "https://covers.openlibrary.org/b/id/8226191-L.jpg",
        },
        {
            id: 2,
            title: "Atomic Habits",
            author: "James Clear",
            isbn: "978-0735211292",
            copies: 4,
            available: 0,
            image_url: "https://images-na.ssl-images-amazon.com/images/I/51-nXsSRfZL._SX329_BO1,204,203,200_.jpg",
        },
        {
            id: 3,
            title: "The Pragmatic Programmer",
            author: "Andrew Hunt",
            isbn: "978-0201616224",
            copies: 2,
            available: 2,
            image_url: "https://covers.openlibrary.org/b/id/8679946-L.jpg",
        },
        {
            id: 4,
            title: "Design Patterns",
            author: "Erich Gamma",
            isbn: "978-0201633610",
            copies: 3,
            available: 1,
            image_url: "https://covers.openlibrary.org/b/id/8276197-L.jpg",
        },
        {
            id: 5,
            title: "Introduction to Algorithms",
            author: "Thomas H. Cormen",
            isbn: "978-0262033848",
            copies: 4,
            available: 4,
            image_url: "https://covers.openlibrary.org/b/id/8081836-L.jpg",
        },
        {
            id: 6,
            title: "Refactoring",
            author: "Martin Fowler",
            isbn: "978-0201485677",
            copies: 2,
            available: 0,
            image_url: "https://covers.openlibrary.org/b/id/8231851-L.jpg",
        },
        {
            id: 7,
            title: "JavaScript: The Good Parts",
            author: "Douglas Crockford",
            isbn: "978-0596517748",
            copies: 3,
            available: 2,
            image_url: "https://covers.openlibrary.org/b/id/8131351-L.jpg",
        },
        {
            id: 8,
            title: "Clean Code",
            author: "Robert C. Martin",
            isbn: "978-0132350884",
            copies: 5,
            available: 3,
            image_url: "https://covers.openlibrary.org/b/id/8226191-L.jpg",
        },
        {
            id: 9,
            title: "Atomic Habits",
            author: "James Clear",
            isbn: "978-0735211292",
            copies: 4,
            available: 0,
            image_url: "https://images-na.ssl-images-amazon.com/images/I/51-nXsSRfZL._SX329_BO1,204,203,200_.jpg",
        },
        {
            id: 10,
            title: "The Pragmatic Programmer",
            author: "Andrew Hunt",
            isbn: "978-0201616224",
            copies: 2,
            available: 2,
            image_url: "https://covers.openlibrary.org/b/id/8679946-L.jpg",
        },
        {
            id: 11,
            title: "Design Patterns",
            author: "Erich Gamma",
            isbn: "978-0201633610",
            copies: 3,
            available: 1,
            image_url: "https://covers.openlibrary.org/b/id/8276197-L.jpg",
        },
        {
            id: 12,
            title: "Introduction to Algorithms",
            author: "Thomas H. Cormen",
            isbn: "978-0262033848",
            copies: 4,
            available: 4,
            image_url: "https://covers.openlibrary.org/b/id/8081836-L.jpg",
        },
        {
            id: 13,
            title: "Refactoring",
            author: "Martin Fowler",
            isbn: "978-0201485677",
            copies: 2,
            available: 0,
            image_url: "https://covers.openlibrary.org/b/id/8231851-L.jpg",
        },
        {
            id: 14,
            title: "JavaScript: The Good Parts",
            author: "Douglas Crockford",
            isbn: "978-0596517748",
            copies: 3,
            available: 2,
            image_url: "https://covers.openlibrary.org/b/id/8131351-L.jpg",
        },
    ];

    return <ul className="booklist">
        {
            dummyBooks.map((book) => {
                return <li key={book.id}><BookCard book={book} /></li>
            })
        }
    </ul>


}

export default BookList;