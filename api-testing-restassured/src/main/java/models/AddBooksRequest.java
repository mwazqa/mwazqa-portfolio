package models;

import java.util.List;

public class AddBooksRequest {

    private String userId;
    private List<IsbnItem> collectionOfIsbns;

    public AddBooksRequest(String userId, List<IsbnItem> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public List<IsbnItem> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public static class IsbnItem {
        private String isbn;

        public IsbnItem(String isbn) {
            this.isbn = isbn;
        }

        public String getIsbn() {
            return isbn;
        }
    }
}

