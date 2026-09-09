const API_BASE_URL = "http://localhost:8080/api/departments";

export interface Department {
  id: number;
  name: string;
}

export interface DepartmentRequest {
  name: string;
}

export async function getAllDepartments(): Promise<Department[]> {
  const res = await fetch(API_BASE_URL, { cache: "no-store" });
  if (!res.ok) {
    throw new Error("Failed to fetch departments");
  }
  return res.json();
}

export async function createDepartment(data: DepartmentRequest): Promise<Department> {
  const res = await fetch(API_BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) {
    const errorBody = await res.json().catch(() => null);
    throw new Error(errorBody?.message || "Failed to create department");
  }

  return res.json();
}