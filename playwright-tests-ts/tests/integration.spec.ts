import { test, expect } from '../src/fixtures/api-fixtures';

test('Integration: Book store listing is accessible', async ({ api }) => {
  const res = await api.get('/BookStore/v1/Books');
  expect(res.status).toBeGreaterThanOrEqual(200);
  expect(res.status).toBeLessThan(300);
  // Body may be an object with 'books' or an array depending on API
  const body = res.body as any;
  const hasBooks = Array.isArray(body) || Array.isArray(body?.books);
  expect(hasBooks).toBeTruthy();
});


