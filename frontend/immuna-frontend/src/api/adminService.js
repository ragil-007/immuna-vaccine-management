import api from "./axios"

export const getDashboardSummary = async () => {
  const response = await api.get("/admin/home")
  return response.data
}