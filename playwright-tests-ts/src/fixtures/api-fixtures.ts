import { test as base, expect, APIRequestContext } from '@playwright/test';
import { ApiHelper } from '../helpers/api-helper';

// Extend base test to provide an `api` fixture (ApiHelper) that wraps Playwright's request
export const test = base.extend<{ api: ApiHelper }>({
  api: async ({ request }, use) => {
	const helper = new ApiHelper(request as APIRequestContext);
	await use(helper);
  },
});

export { expect };


