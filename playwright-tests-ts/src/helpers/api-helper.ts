import { APIRequestContext } from '@playwright/test';
import { ConfigManager } from '../utils/config-manager';

/**
 * API Helper - centralized API request handling
 */
export class ApiHelper {
  private request: APIRequestContext;
  private baseURL: string;

  constructor(request: APIRequestContext) {
    this.request = request;
    this.baseURL = ConfigManager.getBaseUrl();
  }

  /**
   * Generic GET request
   */
  async get(endpoint: string, options: any = {}): Promise<any> {
    const url = `${this.baseURL}${endpoint}`;
    const response = await this.request.get(url, options);
    return {
      status: response.status(),
      body: await response.json().catch(() => response.text()),
      headers: response.headers(),
      response,
    };
  }

  /**
   * Generic POST request
   */
  async post(endpoint: string, data: any, options: any = {}): Promise<any> {
    const url = `${this.baseURL}${endpoint}`;
    const response = await this.request.post(url, {
      ...options,
      data,
    });
    return {
      status: response.status(),
      body: await response.json().catch(() => response.text()),
      headers: response.headers(),
      response,
    };
  }

  /**
   * Generic DELETE request
   */
  async delete(endpoint: string, options: any = {}): Promise<any> {
    const url = `${this.baseURL}${endpoint}`;
    const response = await this.request.delete(url, options);
    return {
      status: response.status(),
      body: await response.json().catch(() => response.text()),
      headers: response.headers(),
      response,
    };
  }

  /**
   * Login and get token
   */
  async login(userName: string, password: string): Promise<string> {
    const result = await this.post('/Account/v1/GenerateToken', {
      userName,
      password,
    });
    const body = result.body as any;
    if (body.token) {
      return body.token;
    }
    throw new Error(`Failed to login: ${JSON.stringify(result.body)}`);
  }

  /**
   * Get user profile with Bearer token
   */
  async getUserProfile(token: string, username: string): Promise<any> {
    return this.get(`/Account/v1/User/${username}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * Add books to collection with Bearer token
   */
  async addBooks(token: string, userId: string, isbns: string[]): Promise<any> {
    return this.post(
      '/BookStore/v1/Books',
      {
        userId,
        collectionOfIsbns: isbns.map((isbn) => ({ isbn })),
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  /**
   * Remove book from collection with Bearer token
   */
  async removeBook(token: string, isbn: string): Promise<any> {
    return this.delete('/BookStore/v1/Book', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
      params: {
        ISBN: isbn,
      },
    });
  }
}

