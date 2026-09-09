"use client";

import { FormEvent, useState } from "react";
import {
  cancelNomination,
  createNomination,
  getNominations,
  Nomination,
} from "@/services/nominationService";

export default function NominationsPage() {
  const [programId, setProgramId] = useState("");
  const [officerId, setOfficerId] = useState("");
  const [nominations, setNominations] = useState<Nomination[]>([]);
  const [message, setMessage] = useState("");

  async function refresh() {
    try {
      setNominations(await getNominations(Number(programId)));
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not load nominations.");
    }
  }

  async function submit(event: FormEvent) {
    event.preventDefault();
    try {
      await createNomination(Number(officerId), Number(programId));
      setMessage("Nomination created.");
      await refresh();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not create nomination.");
    }
  }

  async function cancel(id: number) {
    try {
      await cancelNomination(id);
      setMessage("Nomination cancelled and the next waitlisted officer was promoted.");
      await refresh();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not cancel nomination.");
    }
  }

  return (
    <main style={{ maxWidth: 700, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h1>Nominations</h1>
      <form onSubmit={submit} style={{ display: "flex", gap: 8 }}>
        <input required type="number" placeholder="Programme ID" value={programId} onChange={(e) => setProgramId(e.target.value)} />
        <input required type="number" placeholder="Officer ID" value={officerId} onChange={(e) => setOfficerId(e.target.value)} />
        <button type="submit">Nominate</button>
        <button type="button" onClick={refresh}>Refresh</button>
      </form>
      <p>{message}</p>
      <ul>
        {nominations.map((nomination) => (
          <li key={nomination.id}>
            #{nomination.id} — {nomination.officerName} — {nomination.status}
            {nomination.status !== "CANCELLED" && (
              <button onClick={() => cancel(nomination.id)} style={{ marginLeft: 8 }}>
                Cancel
              </button>
            )}
          </li>
        ))}
      </ul>
    </main>
  );
}
