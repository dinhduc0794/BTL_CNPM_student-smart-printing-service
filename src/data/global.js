const globalDataStore = {
  data: {},

  addActionData(key, value) {
    this.data[key] = [value]; 
  },

  getActionData(key) {
    return this.data[key] || [];
  },
};

export default globalDataStore;
