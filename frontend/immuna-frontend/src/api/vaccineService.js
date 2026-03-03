import api from "./axios"

export const getVaccines = async () => {
  return (await api.get("/admin/vaccines")).data
}

export const createVaccine = async (vaccine) => {
  return (await api.post("/admin/vaccines", vaccine)).data
}

export const updateVaccine = async (id, vaccine) => {
  return (await api.put(`/admin/vaccines/${id}`, vaccine)).data
}

export const deleteVaccine = async (id) => {
  await api.delete(`/admin/vaccines/${id}`)
}