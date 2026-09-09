import { API_BASE_URL, getApiError } from "./api";

export type NominationStatus = "CONFIRMED" | "WAITLISTED" | "CANCELLED";

export interface Nomination {
  id: number;
  officerId: number;
  officerName: string;
  trainingProgramId: number;
  trainingProgramName: string;
  status: NominationStatus;
  nominatedAt: string;
}

const URL = `${API_BASE_URL}/api/nominations`;

export async function getNominations(programId: number): Promise<Nomination[]> {
  const response = await fetch(`${URL}/program/${programId}`);
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<Nomination[]>;
}

export async function createNomination(
  officerId: number,
  trainingProgramId: number,
): Promise<Nomination> {
  const response = await fetch(URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ officerId, trainingProgramId }),
  });
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<Nomination>;
}

export async function cancelNomination(id: number): Promise<Nomination> {
  const response = await fetch(`${URL}/${id}`, { method: "DELETE" });
  if (!response.ok) throw new Error(await getApiError(response));
  return response.json() as Promise<Nomination>;
}
