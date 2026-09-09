import { API_BASE_URL, getApiError } from "./api";

export interface TrainingProgram {
  id: number;
  name: string;
  maximumParticipants: number;
}

const URL = `${API_BASE_URL}/api/training-programs`;

export async function getAllTrainingPrograms(): Promise<TrainingProgram[]> {
  const response = await fetch(URL);
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<TrainingProgram[]>;
}

export async function createTrainingProgram(
  request: Omit<TrainingProgram, "id">,
): Promise<TrainingProgram> {
  const response = await fetch(URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request),
  });
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<TrainingProgram>;
}
