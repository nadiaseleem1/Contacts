package com.example.contacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemContactBinding
import com.example.contacts.model.Contact

class ContactsAdapter(val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.name.text = contact.name
        holder.binding.phone.text = contact.phone
        if (onContactClickListener != null) {
            holder.itemView.setOnClickListener {
                onContactClickListener?.onClick(contact)
            }
        }
    }

    var onContactClickListener: OnContactClickListener? = null

    fun interface OnContactClickListener {
        fun onClick(contact: Contact)
    }
}