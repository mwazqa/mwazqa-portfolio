/**
 * API models and request/response interfaces
 */

export interface LoginRequest {
  userName: string;
  password: string;
}

export interface LoginResponse {
  userID?: string;
  username?: string;
  token?: string;
  expires?: string;
  result?: string;
  message?: string;
  status?: string;
}

export interface RegisterRequest {
  userName: string;
  password: string;
  firstname: string;
  lastname: string;
}

export interface RegisterResponse {
  userID?: string;
  username?: string;
  books?: object[];
  code?: string;
  message?: string;
}

export interface BookStoreResponse {
  isbn?: string;
  title?: string;
  subTitle?: string;
  author?: string;
  publishDate?: string;
  publisher?: string;
  pages?: number;
  description?: string;
  website?: string;
}

export interface AddBooksRequest {
  userId: string;
  collectionOfIsbns: { isbn: string }[];
}

export interface AddBooksResponse {
  books?: object[];
}

export interface BookCollectionItem {
  isbn: string;
  title: string;
}

export interface UserProfileResponse {
  userID?: string;
  username?: string;
  firstName?: string;
  lastName?: string;
  books?: BookCollectionItem[];
}

export interface LinksResponse {
  statusCode?: number;
  URL?: string;
}

