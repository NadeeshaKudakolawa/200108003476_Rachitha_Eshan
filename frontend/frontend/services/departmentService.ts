export interface Department {
  id: number;
  name: string;
}

export interface DepartmentRequest {
  name: string;
}

const API_BASE_URL =
  process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";
const DEPARTMENTS_URL = `${API_BASE_URL}/api/departments`;

async function getErrorMessage(response: Response): Promise<string> {
  try {
    const body = (await response.json()) as { message?: string };
    if (body.message) {
      return body.message;
    }
  } catch {
    // Use the HTTP status when the backend does not return JSON.
  }

  return `Request failed with status ${response.status}.`;
}

export async function getAllDepartments(): Promise<Department[]> {
  const response = await fetch(DEPARTMENTS_URL);

  if (!response.ok) {
    throw new Error(await getErrorMessage(response));
  }

  return response.json() as Promise<Department[]>;
}

export async function createDepartment(
  request: DepartmentRequest,
): Promise<Department> {
  const response = await fetch(DEPARTMENTS_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response));
  }

  return response.json() as Promise<Department>;
}
