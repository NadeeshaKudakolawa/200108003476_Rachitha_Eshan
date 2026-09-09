"use client";

import { FormEvent, useEffect, useState } from "react";
import { createOfficer, getAllOfficers, Officer } from "@/services/officerService";

export default function OfficersPage() {
  const [officers, setOfficers] = useState<Officer[]>([]);
  const [form, setForm] = useState({ employeeNumber: "", name: "", email: "", departmentId: "" });
  const [message, setMessage] = useState("");

  async function load() {
    try {
      setOfficers(await getAllOfficers());
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not load officers.");
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function submit(event: FormEvent) {
    event.preventDefault();
    try {
      await createOfficer({ ...form, departmentId: Number(form.departmentId) });
      setForm({ employeeNumber: "", name: "", email: "", departmentId: "" });
      setMessage("Officer created.");
      await load();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not create officer.");
    }
  }

  return (
    <main style={{ maxWidth: 700, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h1>Officers</h1>
      <form onSubmit={submit} style={{ display: "grid", gap: 8, marginBottom: 20 }}>
        {(["employeeNumber", "name", "email", "departmentId"] as const).map((field) => (
          <input
            key={field}
            required
            type={field === "departmentId" ? "number" : field === "email" ? "email" : "text"}
            placeholder={field}
            value={form[field]}
            onChange={(e) => setForm({ ...form, [field]: e.target.value })}
          />
        ))}
        <button type="submit">Add officer</button>
      </form>
      <p>{message}</p>
      <ul>
        {officers.map((officer) => (
          <li key={officer.id}>
            #{officer.id} — {officer.name} ({officer.email}) — {officer.departmentName}
          </li>
        ))}
      </ul>
    </main>
  );
}
