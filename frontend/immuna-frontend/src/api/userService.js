import api from "./axios"

export const getUsers = async () => {
  return (await api.get("/admin/users")).data
}

export const getNormalUsers = async () => {
  return (await api.get("/admin/users/normal")).data
}

export const enableUser = async (id) => {
  return await api.put(`/admin/users/${id}/enable`)
}

export const disableUser = async (id) => {
  return await api.put(`/admin/users/${id}/disable`)
}

export const deleteUser = async (id) => {
  return await api.delete(`/admin/users/${id}`)
}