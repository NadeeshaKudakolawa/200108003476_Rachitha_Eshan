import { API_BASE_URL, getApiError } from "./api";

export interface Officer {
  id: number;
  employeeNumber: string;
  name: string;
  email: string;
  departmentId: number;
  departmentName: string;
}

export interface OfficerRequest {
  employeeNumber: string;
  name: string;
  email: string;
  departmentId: number;
}

const URL = `${API_BASE_URL}/api/officers`;

export async function getAllOfficers(): Promise<Officer[]> {
  const response = await fetch(URL);
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<Officer[]>;
}

export async function createOfficer(request: OfficerRequest): Promise<Officer> {
  const response = await fetch(URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request),
  });
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<Officer>;
}
