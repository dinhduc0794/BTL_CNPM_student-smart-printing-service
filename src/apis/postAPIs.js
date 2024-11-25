

export async function scheduleAction({ request }) {
  const formData = await request.formData();
  const data = {
    date: formData.get('date'),
    time: formData.get('time'),
    note: formData.get('note'),
  };

  console.log(data);
  

  return 0;
}

export async function setOptionAction({ request }) {
  const formData = await request.formData();
  
  const data = {
    pageSize: formData.get('pageSize'),
    countCopies: formData.get('countCopies'),
    isOneSide: formData.get('isOneSide'),
    isColor: formData.get('isColor')
  };

  console.log(data);
  

  return 0;
}
