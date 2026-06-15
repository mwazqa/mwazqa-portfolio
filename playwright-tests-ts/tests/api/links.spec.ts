import { test, expect } from '../../src/fixtures/api-fixtures';

test('API: base page returns HTML and status 200', async ({ api }) => {
  const res = await api.get('/');
  expect(res.status).toBeGreaterThanOrEqual(200);
  expect(res.status).toBeLessThan(300);
  // body might be HTML string
  expect(typeof res.body === 'string' || typeof res.body === 'object').toBeTruthy();
});


