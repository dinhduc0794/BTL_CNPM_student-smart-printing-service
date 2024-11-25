import { userData } from '~/data/mockdata'

export async function historyLoader() {
  const user = userData;

  return { user };
}
