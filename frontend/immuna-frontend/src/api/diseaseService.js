import api from "./axios"

export const getDiseases = async () => {
  const response = await api.get("/admin/diseases")
  return response.data
}

export const createDisease = async (disease) => {
  const response = await api.post("/admin/diseases", disease)
  return response.data
}

export const updateDisease = async (id, disease) => {
  const response = await api.put(`/admin/diseases/${id}`, disease)
  return response.data
}

export const deleteDisease = async (id) => {
  await api.delete(`/admin/diseases/${id}`)
}